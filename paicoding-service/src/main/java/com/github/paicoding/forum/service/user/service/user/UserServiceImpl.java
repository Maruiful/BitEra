package com.github.paicoding.forum.service.user.service.user;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
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
import com.github.paicoding.forum.service.article.repository.dao.ArticleDao;
import com.github.paicoding.forum.service.statistics.service.CountService;
import com.github.paicoding.forum.service.user.converter.UserConverter;
import com.github.paicoding.forum.service.user.repository.dao.UserAiDao;
import com.github.paicoding.forum.service.user.repository.dao.UserDao;
import com.github.paicoding.forum.service.user.repository.dao.UserRelationDao;
import com.github.paicoding.forum.service.user.repository.entity.UserAiDO;
import com.github.paicoding.forum.service.user.repository.entity.UserDO;        
import com.github.paicoding.forum.service.user.repository.entity.UserInfoDO;
import com.github.paicoding.forum.service.user.repository.entity.UserRelationDO;
import com.github.paicoding.forum.service.user.service.UserAiService;
import com.github.paicoding.forum.service.user.service.UserService;
import com.github.paicoding.forum.service.user.service.help.UserPwdEncoder;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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

    @Autowired
    private UserAiDao userAiDao;
    @Autowired
    private CountService countService;
    @Autowired
    private UserRelationDao userRelationDao;
    @Autowired
    private ArticleDao articleDao;

    @Override
    public UserDO getWxUser(String wxuuid) { return null; }

    @Override
    public List<SimpleUserInfoDTO> searchUser(String userName) { return null; } 

    @Override
    public void saveUserInfo(UserInfoSaveReq req) {
        UserInfoDO userInfoDO = UserConverter.toDO(req);
        userDao.updateUserInfo(userInfoDO);
    }

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
    public List<SimpleUserInfoDTO> batchQuerySimpleUserInfo(Collection<Long> userIds) {
        List<UserInfoDO> users = userDao.getByUserIds(userIds);
        if (CollectionUtils.isEmpty(users)) {
            return Collections.emptyList();
        }
        return users.stream().map(UserConverter::toSimpleInfo).collect(Collectors.toList());
    }

    @Override
    public List<BaseUserInfoDTO> batchQueryBasicUserInfo(Collection<Long> userIds) { return null; }                                                             

    @Override
    public UserStatisticInfoDTO queryUserInfoWithStatistic(Long userId) {
        BaseUserInfoDTO userInfoDTO = queryBasicUserInfo(userId);
        UserAiDO aiDO = userAiDao.getByUserId(userId);
        if (aiDO != null) {
            userInfoDTO.setStarNumber(aiDO.getStarNumber());
            userInfoDTO.setExpireTime(aiDO.getStarExpireTime());
            userInfoDTO.setStarStatus(UserAIStatEnum.fromCode(aiDO.getState()));
        }

        UserStatisticInfoDTO userHomeDTO = countService.queryUserStatisticInfo(userId);
        userHomeDTO = UserConverter.toUserHomeDTO(userHomeDTO, userInfoDTO);

        // 用户资料完整度
        int cnt = 0;
        if (StringUtils.isNotBlank(userHomeDTO.getCompany())) {
            ++cnt;
        }
        if (StringUtils.isNotBlank(userHomeDTO.getPosition())) {
            ++cnt;
        }
        if (StringUtils.isNotBlank(userHomeDTO.getProfile())) {
            ++cnt;
        }
        userHomeDTO.setInfoPercent(cnt * 100 / 3);

        // 是否关注
        Long followUserId = ReqInfoContext.getReqInfo().getUserId();
        if (followUserId != null) {
            UserRelationDO userRelationDO = userRelationDao.getUserRelationByUserId(userId, followUserId);
            userHomeDTO.setFollowed((userRelationDO == null) ? Boolean.FALSE : Boolean.TRUE);
        } else {
            userHomeDTO.setFollowed(Boolean.FALSE);
        }

        // 加入天数
        int joinDayCount = (int) ((System.currentTimeMillis() - userHomeDTO.getCreateTime()
                .getTime()) / (1000 * 3600 * 24));
        userHomeDTO.setJoinDayCount(Math.max(1, joinDayCount));

        // 创作历程
        List<YearArticleDTO> yearArticleDTOS = articleDao.listYearArticleByUserId(userId);
        userHomeDTO.setYearArticleList(yearArticleDTOS);
        return userHomeDTO;
    }

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