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

    @Override
    public boolean hasPayed(Long article, Long currentUerId) { return false; }

    @Override
    public ArticlePayInfoDTO toPay(Long articleId, Long currentUserId, String notes) { return null; }

    @Override
    public boolean updatePaying(Long payId, Long currentUserId, String notes) { return false; }

    @Override
    public boolean updatePayStatus(Long payId, String verifyCode, PayStatusEnum payStatus,
                                   Long payTime, String transactionId) { return false; }

    @Override
    public PayConfirmDTO buildPayConfirmInfo(Long payId, ArticlePayRecordDO record) { return null; }

    @Override
    public List<SimpleUserInfoDTO> queryPayUsers(Long articleId) { return null; }
}