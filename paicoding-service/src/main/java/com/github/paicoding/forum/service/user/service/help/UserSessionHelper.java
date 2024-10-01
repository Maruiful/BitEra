package com.github.paicoding.forum.service.user.service.help;

import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 使用jwt来存储用户token，则不需要后端来存储session了
 * */
@Slf4j
@Component
public class UserSessionHelper {
    @Component
    @Data
    @ConfigurationProperties("paicoding.jwt")
    public static class JwtProperties {
        /**
         * 签发人
         */
        private String issuer;
        /**
         * 密钥
         */
        private String secret;
        /**
         * 有效期，毫秒时间戳
         */
        private Long expire;
    }

    private final JwtProperties jwtProperties;

    private Algorithm algorithm;

    public UserSessionHelper(JwtProperties jwtProperties) {
        this.jwtProperties = jwtProperties;
    }

    public String genSession(Long userId) { return null; }

    public void removeSession(String session) {}

    /**
     * 根据会话获取用户信息
     *
     * @param session
     * @return
     */
    public Long getUserIdBySession(String session) { return null; }
}