package com.github.paicoding.forum.web.front.login.wx.helper;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.hui.quick.plugin.base.Base64Util;
import com.github.hui.quick.plugin.base.DomUtil;
import com.github.hui.quick.plugin.base.constants.MediaType;
import com.github.hui.quick.plugin.qrcode.wrapper.QrCodeGenV3;
import com.github.paicoding.forum.api.model.enums.login.LoginQrTypeEnum;
import com.github.paicoding.forum.core.net.HttpRequestHelper;
import com.github.paicoding.forum.core.util.MapUtils;
import com.github.paicoding.forum.web.front.login.wx.config.WxLoginProperties;
import lombok.Data;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.awt.image.BufferedImage;
import java.util.Map;

/** */
@Slf4j
@Component
public class WxLoginQrGenIntegration {

    private static final String WX_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid={appid}&secret={secret}";
    private static final String WX_GEN_QR_URL = "https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token=";

    private final WxLoginProperties wxLoginProperties;

    //  对于服务号登录的场景

    private volatile WxAccessToken accessToken;

    public WxLoginQrGenIntegration(WxLoginProperties wxLoginProperties) {
        this.wxLoginProperties = wxLoginProperties;
    }

    public LoginQrTypeEnum getLoginQrType()  { return null; }

    /**
     * 生成登录二维码
     *
     * @return
     */
    public String genLoginQrImg(String code)  { return null; }

    private String genQrImg(String qrText) {
        try {
            BufferedImage img;
            if (StringUtils.isBlank(wxLoginProperties.getQrCodeLogo())) {
                img = QrCodeGenV3.of(qrText).asImg();
            } else {
                img = QrCodeGenV3.of(qrText).setLogo(wxLoginProperties.getQrCodeLogo()).asImg();
            }
            return Base64Util.encode(img, "png");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private String getAccessToken() {
        if (!checkAccessToken()) {
            synchronized (this) {
                if (!checkAccessToken()) {
                    refreshAccessToken();
                }
            }
        }

        return accessToken.accessToken;
    }

    /**
     * 校验token的有效性
     *
     * @return true 有效；false 失效
     */
    private boolean checkAccessToken()  { return false; }

    private synchronized void refreshAccessToken() {
        WxAccessToken token = HttpRequestHelper.get(WX_TOKEN_URL,
                MapUtils.create("appid", wxLoginProperties.getAppId(), "secret", wxLoginProperties.getAppSecret()),
                WxAccessToken.class);

        if (token != null && token.accessToken != null) {
            accessToken = token;
            accessToken.expireTimestamp = System.currentTimeMillis() + token.expiresIn * 1000;
        }
        this.accessToken = token;
    }

    /**
     * 生成服务号登录的二维码
     *
     * @return
     * @see <a href="https://developers.weixin.qq.com/doc/service/api/qrcode/qrcodes/api_createqrcode.html"/>
     */
    private String genServiceAccountLoginQrCode(String code)  { return null; }

    @Data
    private static class BaseWxRes {
        @JsonProperty("errcode")
        private String errCode;
        @JsonProperty("errmsg")
        private String errMsg;
    }

    @Data
    @ToString(callSuper = true)
    private static class WxAccessToken extends BaseWxRes {
        // 访问令牌
        @JsonProperty("access_token")
        private String accessToken;
        // 失效时间
        @JsonProperty("expires_in")
        private Integer expiresIn;
        // 令牌失效时间戳
        private Long expireTimestamp = 0L;
    }

    @Data
    @ToString(callSuper = true)
    private static class WxLoginQrCodeRes extends BaseWxRes {
        @JsonProperty("ticket")
        private String ticket;
        @JsonProperty("expire_seconds")
        private String expireSeconds;
        // 二维码内容，需要自己生成对应的二维码图片
        @JsonProperty("url")
        private String url;
    }
}
