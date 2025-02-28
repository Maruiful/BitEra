package com.github.paicoding.forum.service.config.converter;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.github.paicoding.forum.api.model.vo.banner.ConfigReq;
import com.github.paicoding.forum.api.model.vo.banner.dto.ConfigDTO;
import com.github.paicoding.forum.service.config.repository.entity.ConfigDO;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Banner转换
 * */
public class ConfigConverter {

    public static List<ConfigDTO> toDTOS(List<ConfigDO> records) {
        if (CollectionUtils.isEmpty(records)) {
            return Collections.emptyList();
        }
        return records.stream().map(ConfigConverter::toDTO).collect(Collectors.toList());
    }

    public static ConfigDTO toDTO(ConfigDO configDO) { return null; }

    public static ConfigDO toDO(ConfigReq configReq) { return null; }
}