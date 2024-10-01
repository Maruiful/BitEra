package com.github.paicoding.forum.service.pay.service.integration.wx;

import com.github.paicoding.forum.api.model.context.ReqInfoContext;
import com.github.paicoding.forum.api.model.enums.pay.ThirdPayWayEnum;
import com.github.paicoding.forum.core.util.JsonUtil;
import com.github.paicoding.forum.service.pay.config.WxPayConfig;
import com.github.paicoding.forum.service.pay.model.PayCallbackBo;
import com.github.paicoding.forum.service.pay.model.ThirdPayOrderReqBo;
import com.wechat.pay.java.core.Config;
import com.wechat.pay.java.core.RSAAutoCertificateConfig;
import com.wechat.pay.java.service.payments.model.Transaction;
import com.wechat.pay.java.service.payments.nativepay.NativePayService;
import com.wechat.pay.java.service.payments.nativepay.model.Amount;
import com.wechat.pay.java.service.payments.nativepay.model.CloseOrderRequest;
import com.wechat.pay.java.service.payments.nativepay.model.PrepayRequest;
import com.wechat.pay.java.service.payments.nativepay.model.PrepayResponse;
import com.wechat.pay.java.service.payments.nativepay.model.QueryOrderByOutTradeNoRequest;
import com.wechat.pay.java.service.payments.nativepay.model.SceneInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.stereotype.Service;

/** */
@Slf4j
@Service
@ConditionalOnBean(WxPayConfig.class)
public class NativeWxPayIntegration extends AbsWxPayIntegration {
    private NativePayService nativePayService;

    public NativeWxPayIntegration(WxPayConfig wxPayConfig) {
        this.wxPayConfig = wxPayConfig;
        Config config = new RSAAutoCertificateConfig.Builder()
                .merchantId(wxPayConfig.getMerchantId())
                .privateKey(wxPayConfig.getPrivateKeyContent())
                .merchantSerialNumber(wxPayConfig.getMerchantSerialNumber())
                .apiV3Key(wxPayConfig.getApiV3Key())
                .build();
        nativePayService = new NativePayService.Builder().config(config).build();
    }

    @Override
    public boolean support(ThirdPayWayEnum payWay)  { return false; }

    /**
     * native 支付，生成扫描支付二维码唤起微信支付页面
     *
     * @return 形如 wx://xxx 的支付二维码
     */
    public String createPayOrder(ThirdPayOrderReqBo payReq)  { return null; }

    public void closeOrder(String outTradeNo)  {}

    public PayCallbackBo queryOrder(String outTradeNo)  { return null; }
}
