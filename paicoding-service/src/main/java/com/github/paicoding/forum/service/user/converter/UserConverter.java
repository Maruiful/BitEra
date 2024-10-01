package com.github.paicoding.forum.service.user.converter;

import com.github.paicoding.forum.api.model.vo.user.UserInfoSaveReq;
import com.github.paicoding.forum.api.model.vo.user.UserRelationReq;
import com.github.paicoding.forum.api.model.vo.user.UserSaveReq;
import com.github.paicoding.forum.api.model.vo.user.dto.BaseUserInfoDTO;
import com.github.paicoding.forum.api.model.vo.user.dto.SimpleUserInfoDTO;
import com.github.paicoding.forum.api.model.vo.user.dto.UserStatisticInfoDTO;
import com.github.paicoding.forum.service.user.repository.entity.UserAiDO;
import com.github.paicoding.forum.service.user.repository.entity.UserDO;
import com.github.paicoding.forum.service.user.repository.entity.UserInfoDO;
import com.github.paicoding.forum.service.user.repository.entity.UserRelationDO;

/**
 * 用户转换
 * */
public class UserConverter {

    public static UserDO toDO(UserSaveReq req) { return null; }

    public static UserInfoDO toDO(UserInfoSaveReq req) { return null; }

    public static BaseUserInfoDTO toDTO(UserInfoDO info, UserAiDO userAiDO) { return null; }

    public static BaseUserInfoDTO toDTO(UserInfoDO info) { return null; }

    public static SimpleUserInfoDTO toSimpleInfo(UserInfoDO info) { return null; }

    public static UserRelationDO toDO(UserRelationReq req) { return null; }

    public static UserStatisticInfoDTO toUserHomeDTO(UserStatisticInfoDTO userHomeDTO, BaseUserInfoDTO baseUserInfoDTO) { return null; }
}