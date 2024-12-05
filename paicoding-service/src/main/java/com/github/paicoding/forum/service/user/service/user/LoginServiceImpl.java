package com.github.paicoding.forum.service.user.service.user;

import com.github.paicoding.forum.api.model.context.ReqInfoContext;
import com.github.paicoding.forum.api.model.exception.ExceptionUtil;
import com.github.paicoding.forum.api.model.vo.constants.StatusEnum;
import com.github.paicoding.forum.api.model.vo.user.UserPwdLoginReq;
import com.github.paicoding.forum.api.model.vo.user.UserZsxqLoginReq;
import com.github.paicoding.forum.core.util.StarNumberUtil;
import com.github.paicoding.forum.service.user.repository.dao.UserAiDao;
import com.github.paicoding.forum.service.user.repository.dao.UserDao;
import com.github.paicoding.forum.service.user.repository.entity.UserAiDO;
import com.github.paicoding.forum.service.user.repository.entity.UserDO;
import com.github.paicoding.forum.service.user.service.LoginService;
import com.github.paicoding.forum.service.user.service.RegisterService;
import com.github.paicoding.forum.service.user.service.UserAiService;
import com.github.paicoding.forum.service.user.service.UserService;
import com.github.paicoding.forum.service.user.service.help.StarNumberHelper;
import com.github.paicoding.forum.service.user.service.help.UserPwdEncoder;
import com.github.paicoding.forum.service.user.service.help.UserSessionHelper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 登录相关服务类
 */
@Slf4j
@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private UserDao userDao;
    @Autowired
    private UserAiService userAiService;

    @Autowired
    private UserPwdEncoder userPwdEncoder;

    @Autowired
    private UserSessionHelper userSessionHelper;

    @Autowired
    private RegisterService registerService;

    @Autowired
    private UserService userService;

    @Autowired
    private StarNumberHelper starNumberHelper;
    @Autowired
    private UserAiDao userAiDao;


    @Override
    public Long autoRegisterWxUserInfo(String uuid) { return null; }

    @Override
    public void logout(String session) {
        userSessionHelper.removeSession(session);
    }

    @Override
    public String loginByWx(Long userId) { return null; }

    @Override
    public String loginByUserPwd(String username, String password) {
        UserDO user = userDao.getUserByUserName(username);
        if (user == null) {
            throw ExceptionUtil.of(StatusEnum.USER_NOT_EXISTS, "userName=" + username);
        }

        if (!userPwdEncoder.match(password, user.getPassword())) {
            throw ExceptionUtil.of(StatusEnum.USER_PWD_ERROR);
        }

        Long userId = user.getId();
        // 1. 为了兼容历史数据，对于首次登录成功的用户，初始化ai信息
        userAiService.initOrUpdateAiInfo(new UserPwdLoginReq().setUserId(userId).setUsername(username).setPassword(password));

        // 登录成功，返回对应的session
        ReqInfoContext.getReqInfo().setUserId(userId);
        return userSessionHelper.genSession(userId);
    }

    @Override
    public String registerByUserPwd(UserPwdLoginReq loginReq) {
        // 1. 前置校验
        registerPreCheck(loginReq);

        // 2. 判断当前用户是否登录，若已经登录，则直接走绑定流程
        Long userId = ReqInfoContext.getReqInfo().getUserId();
        loginReq.setUserId(userId);
        if (userId != null) {
            // 如果星球编号已经绑定，且已经登录，应该跳转到个人中心页面
            // 2.1 如果用户已经登录，则走绑定用户信息流程
            userService.bindUserInfo(loginReq);
            return ReqInfoContext.getReqInfo().getSession();
        }


        // 3. 尝试使用用户名进行登录，若成功，则依然走绑定流程
        UserDO user = userDao.getUserByUserName(loginReq.getUsername());
        if (user != null) {
            if (!userPwdEncoder.match(loginReq.getPassword(), user.getPassword())) {
                // 3.1 用户名已经存在
                throw ExceptionUtil.of(StatusEnum.USER_LOGIN_NAME_REPEAT, loginReq.getUsername());
            }

            // 3.2 用户存在，尝试走绑定流程
            userId = user.getId();
            loginReq.setUserId(userId);
            userAiService.initOrUpdateAiInfo(loginReq);
        } else {
            //4. 走用户注册流程
            userId = registerService.registerByUserNameAndPassword(loginReq);
        }
        ReqInfoContext.getReqInfo().setUserId(userId);
        return userSessionHelper.genSession(userId);
    }


    /**
     * 注册前置校验
     *
     * @param loginReq
     */
    private void registerPreCheck(UserPwdLoginReq loginReq) {
        if (StringUtils.isBlank(loginReq.getUsername()) || StringUtils.isBlank(loginReq.getPassword())) {
            throw ExceptionUtil.of(StatusEnum.USER_PWD_ERROR);
        }

        String starNumber = loginReq.getStarNumber();
        // 若传了星球信息，首先进行校验
        if (StringUtils.isNotBlank(starNumber)) {
            // 格式化星球编号
            starNumber = StarNumberUtil.formatStarNumber(starNumber);
            loginReq.setStarNumber(starNumber);

            if (Boolean.FALSE.equals(starNumberHelper.checkStarNumber(starNumber))) {
                // 星球编号校验不通过，直接抛异常
                throw ExceptionUtil.of(StatusEnum.USER_STAR_NOT_EXISTS, "星球编号=" + starNumber);
            }
        } else {
            throw ExceptionUtil.of(StatusEnum.USER_STAR_EMPTY);
        }

        UserAiDO userAi = userAiDao.getByStarNumber(loginReq.getStarNumber());
        // 如果星球编号已经被绑定了
        if (userAi != null) {
            throw ExceptionUtil.of(StatusEnum.USER_STAR_REPEAT, loginReq.getStarNumber());
        }

        String invitationCode = loginReq.getInvitationCode();
        if (StringUtils.isNotBlank(invitationCode) && userAiDao.getByInviteCode(invitationCode) == null) {
            // 填写的邀请码不对, 找不到对应的用户
            throw ExceptionUtil.of(StatusEnum.UNEXPECT_ERROR, "非法的邀请码【" + starNumber + "】");
        }
    }
















    @Override
    public String loginByZsxq(UserZsxqLoginReq req) { return null; }
}