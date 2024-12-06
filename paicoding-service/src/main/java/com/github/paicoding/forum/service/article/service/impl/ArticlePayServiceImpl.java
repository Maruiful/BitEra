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

    @Override
    public boolean hasPayed(Long article, Long currentUerId) { return false; }

    @Override
    public ArticlePayInfoDTO toPay(Long articleId, Long currentUserId, String notes) { return null; }

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
    public List<SimpleUserInfoDTO> queryPayUsers(Long articleId) { return null; }
}