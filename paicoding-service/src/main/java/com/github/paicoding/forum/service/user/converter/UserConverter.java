package com.github.paicoding.forum.service.user.converter;

import com.github.paicoding.forum.api.model.enums.RoleEnum;
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
import org.springframework.beans.BeanUtils;

/**
 * 用户转换
 * */
public class UserConverter {

    public static UserDO toDO(UserSaveReq req) { return null; }

    public static UserInfoDO toDO(UserInfoSaveReq req) { return null; }

    public static BaseUserInfoDTO toDTO(UserInfoDO info, UserAiDO userAiDO) { return null; }

    public static BaseUserInfoDTO toDTO(UserInfoDO info) {
        if (info == null) {
            return null;
        }
        BaseUserInfoDTO user = new BaseUserInfoDTO();
        // todo 知识点，bean属性拷贝的几种方式， 直接get/set方式，使用BeanUtil工具类(spring, cglib, apache, objectMapper)，序列化方式等
        BeanUtils.copyProperties(info, user);
        // 设置用户最新登录地理位置
        user.setRegion(info.getIp().getLatestRegion());
        // 设置用户角色
        user.setRole(RoleEnum.role(info.getUserRole()));
        return user;
    }

    public static SimpleUserInfoDTO toSimpleInfo(UserInfoDO info) { return null; }

    public static UserRelationDO toDO(UserRelationReq req) { return null; }

    public static UserStatisticInfoDTO toUserHomeDTO(UserStatisticInfoDTO userHomeDTO, BaseUserInfoDTO baseUserInfoDTO) {
        if (baseUserInfoDTO == null) {
            return null;
        }
        BeanUtils.copyProperties(baseUserInfoDTO, userHomeDTO);
        return userHomeDTO;
    }
}