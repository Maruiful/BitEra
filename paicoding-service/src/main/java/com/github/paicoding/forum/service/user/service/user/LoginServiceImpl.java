package com.github.paicoding.forum.service.user.service.user;

import com.github.paicoding.forum.api.model.vo.user.UserPwdLoginReq;
import com.github.paicoding.forum.api.model.vo.user.UserZsxqLoginReq;
import com.github.paicoding.forum.service.user.service.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 登录相关服务类
 */
@Slf4j
@Service
public class LoginServiceImpl implements LoginService {

    @Override
    public Long autoRegisterWxUserInfo(String uuid) { return null; }

    @Override
    public void logout(String session) {}

    @Override
    public String loginByWx(Long userId) { return null; }

    @Override
    public String loginByUserPwd(String username, String password) { return null; }

    @Override
    public String registerByUserPwd(UserPwdLoginReq loginReq) { return null; }

    @Override
    public String loginByZsxq(UserZsxqLoginReq req) { return null; }
}