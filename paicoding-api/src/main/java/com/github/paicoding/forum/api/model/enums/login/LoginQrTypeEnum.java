package com.github.paicoding.forum.api.model.enums.login;

import lombok.Getter;

@Getter
public enum LoginQrTypeEnum {

    SUBSCRIPTION_ACCOUNT("Subscription Account", "微信公众号"),
    SERVICE_ACCOUNT("Service Account", "服务号"),
    ;
    private String code;
    private String desc;

    LoginQrTypeEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
