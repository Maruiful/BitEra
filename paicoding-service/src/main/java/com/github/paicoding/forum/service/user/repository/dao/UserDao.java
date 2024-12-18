package com.github.paicoding.forum.service.user.repository.dao;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.paicoding.forum.api.model.enums.YesOrNoEnum;
import com.github.paicoding.forum.api.model.vo.PageParam;
import com.github.paicoding.forum.service.user.repository.entity.UserDO;
import com.github.paicoding.forum.service.user.repository.entity.UserInfoDO;
import com.github.paicoding.forum.service.user.repository.mapper.UserInfoMapper;
import com.github.paicoding.forum.service.user.repository.mapper.UserMapper;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;

/**
 * UserDao
 * */
@Repository
public class UserDao extends ServiceImpl<UserInfoMapper, UserInfoDO> {

    @Resource
    private UserMapper userMapper;

    public List<Long> scanUserId(Long userId, Integer size)  { return null; }

    /**
     * 三方账号登录方式
     *
     * @param accountId
     * @return
     */
    public UserDO getByThirdAccountId(String accountId)  { return null; }

    /**
     * 根据用户名来查询
     *
     * @param userName
     * @return
     */
    public List<UserInfoDO> getByUserNameLike(String userName)  { return null; }

    public void saveUser(UserDO user) {
        if (user.getId() == null) {
            userMapper.insert(user);
        } else {
            userMapper.updateById(user);
        }
    }

    public UserInfoDO getByUserId(Long userId)  {
        LambdaQueryWrapper<UserInfoDO> query = Wrappers.lambdaQuery();
        query.eq(UserInfoDO::getUserId, userId)
                .eq(UserInfoDO::getDeleted, YesOrNoEnum.NO.getCode());
        return baseMapper.selectOne(query);
    }

    public List<UserInfoDO> getByUserIds(Collection<Long> userIds)  { return null; }

    public Long getUserCount()  { return null; }

    public void updateUserInfo(UserInfoDO user)  {}

    public UserDO getUserByUserName(String userName)  {
        LambdaQueryWrapper<UserDO> queryUser = Wrappers.lambdaQuery();
        queryUser.eq(UserDO::getUserName, userName)
                .eq(UserDO::getDeleted, YesOrNoEnum.NO.getCode())
                .last("limit 1");
        return userMapper.selectOne(queryUser);
    }

    public UserDO getUserByUserId(Long userId)  {
        return userMapper.selectById(userId);
    }

    public void updateUser(UserDO userDO)  {}
}
