package com.github.paicoding.forum.service.user.service.user;

import com.github.paicoding.forum.api.model.context.ReqInfoContext;
import com.github.paicoding.forum.api.model.enums.user.UserAIStatEnum;
import com.github.paicoding.forum.api.model.exception.ExceptionUtil;
import com.github.paicoding.forum.api.model.vo.article.dto.YearArticleDTO;      
import com.github.paicoding.forum.api.model.vo.constants.StatusEnum;
import com.github.paicoding.forum.api.model.vo.user.UserInfoSaveReq;
import com.github.paicoding.forum.api.model.vo.user.UserPwdLoginReq;
import com.github.paicoding.forum.api.model.vo.user.UserZsxqLoginReq;
import com.github.paicoding.forum.api.model.vo.user.dto.BaseUserInfoDTO;        
import com.github.paicoding.forum.api.model.vo.user.dto.SimpleUserInfoDTO;      
import com.github.paicoding.forum.api.model.vo.user.dto.UserStatisticInfoDTO;
import com.github.paicoding.forum.service.user.converter.UserConverter;
import com.github.paicoding.forum.service.user.repository.dao.UserDao;
import com.github.paicoding.forum.service.user.repository.entity.UserAiDO;
import com.github.paicoding.forum.service.user.repository.entity.UserDO;        
import com.github.paicoding.forum.service.user.repository.entity.UserInfoDO;
import com.github.paicoding.forum.service.user.service.UserAiService;
import com.github.paicoding.forum.service.user.service.UserService;
import com.github.paicoding.forum.service.user.service.help.UserPwdEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

/**
 * 用户Service
 */
@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private UserDao userDao;
    @Autowired
    private UserPwdEncoder userPwdEncoder;

    @Autowired
    private UserAiService userAiService;

    @Override
    public UserDO getWxUser(String wxuuid) { return null; }

    @Override
    public List<SimpleUserInfoDTO> searchUser(String userName) { return null; } 

    @Override
    public void saveUserInfo(UserInfoSaveReq req) {}

    @Override
    public BaseUserInfoDTO getAndUpdateUserIpInfoBySessionId(String session, String clientIp) { return null; }                                                  

    @Override
    public SimpleUserInfoDTO querySimpleUserInfo(Long userId) { return null; }  

    @Override
    public BaseUserInfoDTO queryBasicUserInfo(Long userId) {
        UserInfoDO user = userDao.getByUserId(userId);
        if (user == null) {
            throw ExceptionUtil.of(StatusEnum.USER_NOT_EXISTS, "userId=" + userId);
        }
        return UserConverter.toDTO(user);
    }

    @Override
    public List<SimpleUserInfoDTO> batchQuerySimpleUserInfo(Collection<Long> userIds) { return null; }                                                          

    @Override
    public List<BaseUserInfoDTO> batchQueryBasicUserInfo(Collection<Long> userIds) { return null; }                                                             

    @Override
    public UserStatisticInfoDTO queryUserInfoWithStatistic(Long userId) { return null; }                                                                        

    @Override
    public Long getUserCount() { return null; }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void bindUserInfo(UserPwdLoginReq loginReq) {
        // 0. 绑定用户名 & 密码 前置校验
        UserDO user = userDao.getUserByUserName(loginReq.getUsername());
        if (user == null) {
            // 用户名不存在，则标识当前登录用户可以使用这个用户名
            user = new UserDO();
            user.setId(loginReq.getUserId());
        } else if (!Objects.equals(loginReq.getUserId(), user.getId())) {
            // 登录用户名已经存在了
            throw ExceptionUtil.of(StatusEnum.USER_LOGIN_NAME_REPEAT, loginReq.getUsername());
        }

        // 1. 更新用户名密码
        user.setUserName(loginReq.getUsername());
        user.setPassword(userPwdEncoder.encPwd(loginReq.getPassword()));
        userDao.saveUser(user);

        // 2. 更新ai相关信息
        userAiService.initOrUpdateAiInfo(loginReq);
    }






    @Override
    public BaseUserInfoDTO queryUserByLoginName(String uname) { return null; }  

    @Override
    public void bindUserInfo(UserZsxqLoginReq loginReq) {}

    @Override
    public UserDO getUserDO(Long userId) { return null; }

    @Override
    public UserInfoDO getUserInfo(Long userId) { return null; }

    @Override
    public UserAiDO getUserAiDO(Long userId) { return null; }
}