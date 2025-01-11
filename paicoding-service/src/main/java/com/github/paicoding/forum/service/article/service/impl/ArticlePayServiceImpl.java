package com.github.paicoding.forum.service.article.service.impl;

import com.github.paicoding.forum.api.model.enums.NotifyTypeEnum;
import com.github.paicoding.forum.api.model.enums.pay.PayStatusEnum;
import com.github.paicoding.forum.api.model.enums.pay.ThirdPayWayEnum;
import com.github.paicoding.forum.api.model.exception.ExceptionUtil;
import com.github.paicoding.forum.api.model.vo.article.dto.ArticlePayInfoDTO;
import com.github.paicoding.forum.api.model.vo.article.dto.PayConfirmDTO;
import com.github.paicoding.forum.api.model.vo.constants.StatusEnum;
import com.github.paicoding.forum.api.model.vo.pay.dto.PayInfoDTO;
import com.github.paicoding.forum.api.model.vo.user.dto.BaseUserInfoDTO;
import com.github.paicoding.forum.api.model.vo.user.dto.SimpleUserInfoDTO;
import com.github.paicoding.forum.core.util.DateUtil;
import com.github.paicoding.forum.core.util.PriceUtil;
import com.github.paicoding.forum.core.util.SpringUtil;
import com.github.paicoding.forum.core.util.id.IdUtil;
import com.github.paicoding.forum.service.article.conveter.PayConverter;
import com.github.paicoding.forum.service.article.repository.dao.ArticlePayDao;
import com.github.paicoding.forum.service.article.repository.entity.ArticleDO;
import com.github.paicoding.forum.service.article.repository.entity.ArticlePayRecordDO;
import com.github.paicoding.forum.service.article.service.ArticlePayService;
import com.github.paicoding.forum.service.article.service.ArticleReadService;
import com.github.paicoding.forum.service.notify.help.MsgNotifyHelper;
import com.github.paicoding.forum.service.pay.PayServiceFactory;
import com.github.paicoding.forum.service.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/** */
@Slf4j
@Service
public class ArticlePayServiceImpl implements ArticlePayService {

    @Autowired
    private ArticlePayDao articlePayDao;

    @Autowired
    private UserService userService;
    @Autowired
    private ArticleReadService articleReadService;

    @Autowired
    private PayServiceFactory payServiceFactory;

    @Override
    public boolean hasPayed(Long article, Long currentUerId) { return false; }

    @Override
    public ArticlePayInfoDTO toPay(Long articleId, Long currentUserId, String notes) {
        ArticlePayRecordDO dbRecord = articlePayDao.queryRecordByArticleId(articleId, currentUserId);
        boolean recordChanged = false;
        if (dbRecord == null) {
            // 不存在时，创建一个
            dbRecord = createPayRecord(articleId, currentUserId, notes);
            recordChanged = true;
        }

        // 加事务写锁，防止并发修改支付记录，出现的支付状态不一致的问题
        dbRecord = articlePayDao.selectForUpdate(dbRecord.getId());
        ThirdPayWayEnum payWay = ThirdPayWayEnum.ofPay(dbRecord.getPayWay());

        // 已经支付成功 或者 已经是支付中，则直接返回
        if (Objects.equals(dbRecord.getPayStatus(), PayStatusEnum.SUCCEED.getStatus())
                || Objects.equals(dbRecord.getPayStatus(), PayStatusEnum.PAYING.getStatus())) {
            recordChanged = false;
        } else if (Objects.equals(dbRecord.getPayStatus(), PayStatusEnum.FAIL.getStatus())) {
            // 支付失败，需要重置支付相关信息
            dbRecord.setVerifyCode(IdUtil.genPayCode(payWay, dbRecord.getId()));
            dbRecord.setNotifyTime(null);
            dbRecord.setPayStatus(PayStatusEnum.NOT_PAY.getStatus());
            recordChanged = true;
        } else if (dbRecord.getPrePayExpireTime() == null
                || System.currentTimeMillis() >= dbRecord.getPrePayExpireTime().getTime()) {
            // 未支付、但是唤起支付的verifyCode已经过期的场景
            dbRecord.setVerifyCode(IdUtil.genPayCode(payWay, dbRecord.getId()));
            recordChanged = true;
        } else {
            // 可以直接使用数据库中缓存的用于唤起支付的信息
            recordChanged = false;
        }

        // 收款用户信息
        ArticlePayInfoDTO dto = PayConverter.toPay(dbRecord);
        // 存在数据变更时，需要调用支付服务，重新获取支付相关信息
        PayInfoDTO payInfo = payServiceFactory.getPayService(payWay).toPay(dbRecord, recordChanged);
        if (recordChanged) {
            // 如果数据有变更，执行落库操作
            articlePayDao.updateById(dbRecord);
        }

        // 补齐支付信息
        dto.setPrePayId(payInfo.getPrePayId());
        dto.setPrePayExpireTime(payInfo.getPrePayExpireTime());
        dto.setPayQrCodeMap(payInfo.getPayQrCodeMap());
        return dto;
    }

    @Override
    public boolean updatePaying(Long payId, Long currentUserId, String notes) { return false; }

    @Override
    public boolean updatePayStatus(Long payId, String verifyCode, PayStatusEnum payStatus,
                                   Long payTime, String transactionId) {
        ArticlePayRecordDO dbRecord = articlePayDao.selectForUpdate(payId);
        if (dbRecord == null || !Objects.equals(dbRecord.getVerifyCode(), verifyCode)) {
            throw ExceptionUtil.of(StatusEnum.RECORDS_NOT_EXISTS, "支付记录:" + payId);
        }

        if (Objects.equals(payStatus.getStatus(), dbRecord.getPayStatus())
                || PayStatusEnum.SUCCEED.getStatus().equals(dbRecord.getPayStatus())) {
            // 幂等 or 已支付成功到了终态，不再进行后续的更新
            return true;
        }

        // 更新原来的支付状态为最新的结果
        dbRecord.setPayStatus(payStatus.getStatus());
        dbRecord.setPayCallbackTime(new Date(payTime));
        dbRecord.setUpdateTime(new Date());
        dbRecord.setThirdTransCode(transactionId);
        boolean ans = articlePayDao.updateById(dbRecord);
        if (ans) {
            publishPayStatusChangeNotify(dbRecord);
        }
        return ans;
    }

    /**
     * 根据支付状态发布对应的通知消息
     *
     * @param record
     */
    private void publishPayStatusChangeNotify(ArticlePayRecordDO record) {
        // 支付状态变更的消息回调
        if (Objects.equals(record.getPayStatus(), PayStatusEnum.PAYING.getStatus())) {
            // 更新支付状态为支付中
            MsgNotifyHelper.publish(NotifyTypeEnum.PAYING, record);
        } else if (Objects.equals(record.getPayStatus(), PayStatusEnum.SUCCEED.getStatus())
                || Objects.equals(record.getPayStatus(), PayStatusEnum.FAIL.getStatus())) {
            // 支付成功or失败
            MsgNotifyHelper.publish(NotifyTypeEnum.PAY, record);
        }
    }

    @Override
    public PayConfirmDTO buildPayConfirmInfo(Long payId, ArticlePayRecordDO record) { return null; }

    @Override
    public List<SimpleUserInfoDTO> queryPayUsers(Long articleId) {
        List<Long> users = articlePayDao.querySucceedPayUsersByArticleId(articleId);
        if (CollectionUtils.isEmpty(users)) {
            return Collections.emptyList();
        }

        return userService.batchQuerySimpleUserInfo(users);
    }


    private ArticlePayRecordDO createPayRecord(Long articleId, Long currentUserId, String notes) {
        ArticleDO articleDO = articleReadService.queryBasicArticle(articleId);
        if (articleDO == null) {
            throw ExceptionUtil.of(StatusEnum.RECORDS_NOT_EXISTS, articleId);
        }

        ThirdPayWayEnum payWay = ThirdPayWayEnum.ofPay(articleDO.getPayWay());
        if (payWay == null) {
            // 文章不需要付费阅读
            throw ExceptionUtil.of(StatusEnum.UNEXPECT_ERROR, "文章不需要付费阅读!");
        }

        if (payWay.wxPay() && Objects.equals(SpringUtil.getConfig("view.site.wxPayEnable"), "false")) {
            // 微信支付未开启时，只能走个人收款码方式
            throw ExceptionUtil.of(StatusEnum.UNEXPECT_ERROR, "微信支付未开启，请联系作者换用个人收款码支付方式吧!");
        }

        ArticlePayRecordDO record = new ArticlePayRecordDO();
        record.setArticleId(articleId);
        record.setReceiveUserId(articleDO.getUserId());
        record.setPayUserId(currentUserId);
        record.setPayStatus(PayStatusEnum.NOT_PAY.getStatus());
        record.setNotifyTime(null);
        record.setNotifyCnt(0);
        String mark = String.format("支付解锁阅读《%s》-- %s", articleDO.getTitle(), notes == null ? "" : notes);
        record.setNotes(mark);
        record.setId(IdUtil.genId());
        record.setVerifyCode(IdUtil.genPayCode(payWay, record.getId()));
        record.setPayWay(payWay.getPay());
        record.setPayAmount(articleDO.getPayAmount());
        articlePayDao.save(record);
        return record;
    }
}