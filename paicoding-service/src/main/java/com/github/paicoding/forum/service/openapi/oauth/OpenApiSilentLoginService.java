package com.github.paicoding.forum.service.openapi.oauth;

import com.github.paicoding.forum.api.model.enums.RoleEnum;
import com.github.paicoding.forum.api.model.openapi.user.OpenApiUserDTO;
import com.github.paicoding.forum.service.user.repository.entity.UserAiDO;
import com.github.paicoding.forum.service.user.repository.entity.UserDO;
import com.github.paicoding.forum.service.user.repository.entity.UserInfoDO;
import com.github.paicoding.forum.service.user.service.UserService;
import com.github.paicoding.forum.service.user.service.help.UserSessionHelper;
import org.springframework.stereotype.Service;

@Service
public class OpenApiSilentLoginService {

    private final UserSessionHelper userSessionHelper;
    private final UserService userService;

    public OpenApiSilentLoginService(UserSessionHelper userSessionHelper, UserService userService) {
        this.userSessionHelper = userSessionHelper;
        this.userService = userService;
    }

    /**
     * 使用技术派的Token换取用户信息，从而实现静默登录
     *
     * @param session
     * @return
     */
    public OpenApiUserDTO silentLogin(String session) {
        Long userId = userSessionHelper.getUserIdBySession(session);
        if (userId == null) {
            return null;
        }

        UserDO loginInfo = userService.getUserDO(userId);
        UserInfoDO userInfo = userService.getUserInfo(userId);
        UserAiDO zsxqInfo = userService.getUserAiDO(userId);

        OpenApiUserDTO res = new OpenApiUserDTO();
        res.setUserId(loginInfo.getId());
        res.setLoginName(loginInfo.getUserName());
        res.setWxId(loginInfo.getThirdAccountId());
        res.setUserName(userInfo.getUserName());
        res.setProfile(userInfo.getProfile());
        res.setPhoto(userInfo.getPhoto());
        res.setCompany(userInfo.getCompany());
        res.setPosition(userInfo.getPosition());
        res.setEmail(userInfo.getEmail());
        res.setRole(RoleEnum.role(userInfo.getUserRole()));
        if (zsxqInfo != null) {
            res.setZsxqId(zsxqInfo.getStarNumber());
            res.setZsxqExpireTime(zsxqInfo.getStarExpireTime() == null ? 0L : zsxqInfo.getStarExpireTime().getTime());
        }

        return res;
    }

}
