package com.github.paicoding.forum.service.user.service.relation;

import com.github.paicoding.forum.api.model.context.ReqInfoContext;
import com.github.paicoding.forum.api.model.enums.FollowStateEnum;
import com.github.paicoding.forum.api.model.enums.NotifyTypeEnum;
import com.github.paicoding.forum.api.model.vo.PageListVo;
import com.github.paicoding.forum.api.model.vo.PageParam;
import com.github.paicoding.forum.api.model.vo.notify.NotifyMsgEvent;
import com.github.paicoding.forum.api.model.vo.user.UserRelationReq;
import com.github.paicoding.forum.api.model.vo.user.dto.FollowUserInfoDTO;
import com.github.paicoding.forum.core.util.MapUtils;
import com.github.paicoding.forum.core.util.SpringUtil;
import com.github.paicoding.forum.service.user.converter.UserConverter;
import com.github.paicoding.forum.service.user.repository.dao.UserRelationDao;
import com.github.paicoding.forum.service.user.repository.entity.UserRelationDO;
import com.github.paicoding.forum.service.user.service.UserRelationService;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 用户关系Service
 * */
@Service
public class UserRelationServiceImpl implements UserRelationService {

    @Autowired
    private UserRelationDao userRelationDao;

    @Resource
    

    @Override
    public PageListVo<FollowUserInfoDTO> getUserFollowList(Long userId, PageParam pageParam) { return null; }

    @Override
    public PageListVo<FollowUserInfoDTO> getUserFansList(Long userId, PageParam pageParam) { return null; }

    @Override
    public void updateUserFollowRelationId(PageListVo<FollowUserInfoDTO> followList, Long loginUserId) {}

    @Override
    public Set<Long> getFollowedUserId(List<Long> userIds, Long fansUserId) {
        if (CollectionUtils.isEmpty(userIds)) {
            return Collections.emptySet();
        }

        List<UserRelationDO> relationList = userRelationDao.listUserRelations(fansUserId, userIds);
        Map<Long, UserRelationDO> relationMap = MapUtils.toMap(relationList, UserRelationDO::getUserId, r -> r);
        return relationMap.values().stream().filter(s -> s.getFollowState().equals(FollowStateEnum.FOLLOW.getCode())).map(UserRelationDO::getUserId).collect(Collectors.toSet());
    }

    @Override
    public void saveUserRelation(UserRelationReq req) {
        // 查询是否存在
        UserRelationDO userRelationDO = userRelationDao.getUserRelationRecord(req.getUserId(), ReqInfoContext.getReqInfo().getUserId());
        if (userRelationDO == null) {
            userRelationDO = UserConverter.toDO(req);
            userRelationDao.save(userRelationDO);
            // 发布关注事件
            SpringUtil.publishEvent(new NotifyMsgEvent<>(this, NotifyTypeEnum.FOLLOW, userRelationDO));
            return;
        }

        // 将是否关注状态重置
        userRelationDO.setFollowState(req.getFollowed() ? FollowStateEnum.FOLLOW.getCode() : FollowStateEnum.CANCEL_FOLLOW.getCode());
        userRelationDao.updateById(userRelationDO);
        // 发布关注、取消关注事件
        SpringUtil.publishEvent(new NotifyMsgEvent<>(this, req.getFollowed() ? NotifyTypeEnum.FOLLOW : NotifyTypeEnum.CANCEL_FOLLOW, userRelationDO));
    }
}
