package com.github.paicoding.forum.service.user.service.user;

import com.github.paicoding.forum.service.user.service.UserTransferService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 基于验证码、用户名密码的登录方式
 * */
@Service
@Slf4j
public class UserTransferServiceImpl implements UserTransferService {
   
    @Override
    public boolean transferUser(String uname, String pwd) { return false; }

    @Override
    public boolean transferUser(String starNumber) { return false; }
}