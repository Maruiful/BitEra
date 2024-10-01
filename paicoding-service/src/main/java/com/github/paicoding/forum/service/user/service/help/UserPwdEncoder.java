package com.github.paicoding.forum.service.user.service.help;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;
import java.util.Objects;

/**
 * 密码加密器，后续接入SpringSecurity之后，可以使用 PasswordEncoder 进行替换
 * */
@Component
public class UserPwdEncoder {
    /**
     * 密码加盐，更推荐的做法是每个用户都使用独立的盐，提高安全性
     */
    @Value("${security.salt}")
    private String salt;

    @Value("${security.salt-index}")
    private Integer saltIndex;

    public boolean match(String plainPwd, String encPwd)  { return false; }

    /**
     * 明文密码处理
     *
     * @param plainPwd
     * @return
     */
    public String encPwd(String plainPwd)  { return null; }

}
