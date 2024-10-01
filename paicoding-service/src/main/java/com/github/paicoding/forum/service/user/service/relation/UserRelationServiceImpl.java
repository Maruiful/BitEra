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
    @Resource
    

    @Override
    public PageListVo<FollowUserInfoDTO> getUserFollowList(Long userId, PageParam pageParam) { return null; }

    @Override
    public PageListVo<FollowUserInfoDTO> getUserFansList(Long userId, PageParam pageParam) { return null; }

    @Override
    public void updateUserFollowRelationId(PageListVo<FollowUserInfoDTO> followList, Long loginUserId) {}

    @Override
    public Set<Long> getFollowedUserId(List<Long> userIds, Long fansUserId) { return null; }

    @Override
    public void saveUserRelation(UserRelationReq req) {}
}
