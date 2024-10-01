package com.github.paicoding.forum.service.pay.service.integration.wx;

import com.alibaba.fastjson.JSONObject;
import com.github.paicoding.forum.api.model.context.ReqInfoContext;
import com.github.paicoding.forum.api.model.enums.pay.ThirdPayWayEnum;
import com.github.paicoding.forum.core.util.JsonUtil;
import com.github.paicoding.forum.service.pay.config.WxPayConfig;
import com.github.paicoding.forum.service.pay.model.PayCallbackBo;
import com.github.paicoding.forum.service.pay.model.PrePayInfoResBo;
import com.github.paicoding.forum.service.pay.model.ThirdPayOrderReqBo;
import com.wechat.pay.java.core.Config;
import com.wechat.pay.java.core.RSAAutoCertificateConfig;
import com.wechat.pay.java.service.payments.h5.H5Service;
import com.wechat.pay.java.service.payments.h5.model.*;
import com.wechat.pay.java.service.payments.model.Transaction;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.stereotype.Service;

/** */
@Slf4j
@Service
@ConditionalOnBean(WxPayConfig.class)
public class H5WxPayIntegration extends AbsWxPayIntegration {
    private H5Service h5Service;

    public H5WxPayIntegration(WxPayConfig wxPayConfig) {
        this.wxPayConfig = wxPayConfig;
        Config config = new RSAAutoCertificateConfig.Builder()
                .merchantId(wxPayConfig.getMerchantId())
                .privateKey(wxPayConfig.getPrivateKeyContent())
                .merchantSerialNumber(wxPayConfig.getMerchantSerialNumber())
                .apiV3Key(wxPayConfig.getApiV3Key())
                .build();
        h5Service = new H5Service.Builder().config(config).build();
    }

    @Override
    public boolean support(ThirdPayWayEnum payWay)  { return false; }

    /**
     * h5支付，生成微信支付收银台中间页，适用于拿不到微信给与的用户 OpenId 场景
     *
     * @return
     */
    public String createPayOrder(ThirdPayOrderReqBo payReq)  { return null; }

    @Override
    public void closeOrder(String outTradeNo)  {}

    @Override
    public PayCallbackBo queryOrder(String outTradeNo)  { return null; }

}
