package com.github.paicoding.forum.service.user.service.user;

import com.github.paicoding.forum.api.model.enums.NotifyTypeEnum;
import com.github.paicoding.forum.api.model.enums.user.LoginTypeEnum;
import com.github.paicoding.forum.api.model.exception.ExceptionUtil;
import com.github.paicoding.forum.api.model.vo.constants.StatusEnum;
import com.github.paicoding.forum.api.model.vo.notify.NotifyMsgEvent;
import com.github.paicoding.forum.api.model.vo.user.UserPwdLoginReq;
import com.github.paicoding.forum.core.util.RandUtil;
import com.github.paicoding.forum.core.util.SpringUtil;
import com.github.paicoding.forum.core.util.StarNumberUtil;
import com.github.paicoding.forum.core.util.TransactionUtil;
import com.github.paicoding.forum.service.user.converter.UserAiConverter;       
import com.github.paicoding.forum.service.user.repository.dao.UserAiDao;        
import com.github.paicoding.forum.service.user.repository.dao.UserDao;
import com.github.paicoding.forum.service.user.repository.entity.UserAiDO;      
import com.github.paicoding.forum.service.user.repository.entity.UserDO;        
import com.github.paicoding.forum.service.user.repository.entity.UserInfoDO;    
import com.github.paicoding.forum.service.user.service.RegisterService;
import com.github.paicoding.forum.service.user.service.help.UserPwdEncoder;     
import com.github.paicoding.forum.service.user.service.help.UserRandomGenHelper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * 用户注册服务
 */
@Service
public class RegisterServiceImpl implements RegisterService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserPwdEncoder userPwdEncoder;

    @Autowired
    private UserAiDao userAiDao;

    @Override
    public Long registerSystemUser(String loginUser, String nickUser, String avatar) { return null; }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long registerByUserNameAndPassword(UserPwdLoginReq loginReq) {
        // 1. 判断用户名是否准确
        UserDO user = userDao.getUserByUserName(loginReq.getUsername());
        if (user != null) {
            throw ExceptionUtil.of(StatusEnum.USER_LOGIN_NAME_REPEAT, loginReq.getUsername());
        }

        // 2. 保存用户登录信息
        user = new UserDO();
        user.setUserName(loginReq.getUsername());
        user.setPassword(userPwdEncoder.encPwd(loginReq.getPassword()));
        // 使用传入的thirdAccountId，如果没有则设为空字符串
        user.setThirdAccountId(loginReq.getThirdAccountId() != null ? loginReq.getThirdAccountId() : "");
        // 根据传入的loginType设置，如果没有则默认为用户名密码登录
        user.setLoginType(loginReq.getLoginType() != null ? loginReq.getLoginType() : LoginTypeEnum.USER_PWD.getType());
        userDao.saveUser(user);

        // 3. 保存用户信息
        UserInfoDO userInfo = new UserInfoDO();
        userInfo.setUserId(user.getId());
        userInfo.setUserName(StringUtils.isNoneBlank(loginReq.getDisplayName()) ? loginReq.getDisplayName() : loginReq.getUsername());
        userInfo.setPhoto(StringUtils.isNotBlank(loginReq.getAvatar()) ? loginReq.getAvatar() : UserRandomGenHelper.genAvatar());
        userDao.save(userInfo);

        // 4. 保存ai相互信息
        UserAiDO userAiDO = UserAiConverter.initAi(user.getId(), loginReq.getStarNumber());
        if (loginReq.getStarExpireTime() != null) {
            userAiDO.setStarExpireTime(new Date(loginReq.getStarExpireTime()));
        }
        userAiDao.saveOrUpdateAiBindInfo(userAiDO, loginReq.getInvitationCode());
        processAfterUserRegister(user.getId());
        return user.getId();
    }

    @Override
    public Long registerByWechat(String thirdAccount) { return null; }

    
    public void run() {}


    /**
     * 用户注册完毕之后触发的动作
     *
     * @param userId
     */
    private void processAfterUserRegister(Long userId) {
        TransactionUtil.registryAfterCommitOrImmediatelyRun(new Runnable() {
            @Override
            public void run() {
                // 用户注册事件
                SpringUtil.publishEvent(new NotifyMsgEvent<>(this, NotifyTypeEnum.REGISTER, userId));
            }
        });
    }
}