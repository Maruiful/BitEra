package com.github.paicoding.forum.service.pay.service;

import com.github.paicoding.forum.api.model.enums.pay.PayStatusEnum;
import com.github.paicoding.forum.api.model.enums.pay.ThirdPayWayEnum;
import com.github.paicoding.forum.api.model.vo.pay.dto.PayInfoDTO;
import com.github.paicoding.forum.core.util.JsonUtil;
import com.github.paicoding.forum.core.util.PriceUtil;
import com.github.paicoding.forum.core.util.StrUtil;
import com.github.paicoding.forum.service.article.conveter.PayConverter;
import com.github.paicoding.forum.service.article.repository.entity.ArticlePayRecordDO;
import com.github.paicoding.forum.service.pay.PayService;
import com.github.paicoding.forum.service.pay.model.PayCallbackBo;
import com.github.paicoding.forum.service.pay.model.PrePayInfoResBo;
import com.github.paicoding.forum.service.pay.model.ThirdPayOrderReqBo;
import com.wechat.pay.java.service.refund.model.RefundNotification;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.function.Function;

/**
 * 在线支付流程
 * */
@Slf4j
@Service
public class OnlinePayServiceImpl implements PayService {
    @Autowired
    

    @Override
    public boolean support(ThirdPayWayEnum payWay) { return false; }

    @Override
    public PayInfoDTO toPay(ArticlePayRecordDO record, boolean needRefresh) { return null; }

    @Override
    public boolean paying(ArticlePayRecordDO dbRecord) { return false; }

    @Override
    public ResponseEntity<?> payCallback(HttpServletRequest request, Function<PayCallbackBo, Boolean> payCallback) { return null; }

    @Override
    public ResponseEntity<?> refundCallback(HttpServletRequest request, Function<RefundNotification, Boolean> payCallback) { return null; }
}
