package com.github.paicoding.forum.service.config.service.impl;

import com.github.paicoding.forum.api.model.enums.YesOrNoEnum;
import com.github.paicoding.forum.api.model.vo.PageParam;
import com.github.paicoding.forum.api.model.vo.PageVo;
import com.github.paicoding.forum.api.model.vo.banner.ConfigReq;
import com.github.paicoding.forum.api.model.vo.banner.SearchConfigReq;
import com.github.paicoding.forum.api.model.vo.banner.dto.ConfigDTO;
import com.github.paicoding.forum.core.util.NumUtil;
import com.github.paicoding.forum.service.config.converter.ConfigStructMapper;
import com.github.paicoding.forum.service.config.repository.dao.ConfigDao;
import com.github.paicoding.forum.service.config.repository.entity.ConfigDO;
import com.github.paicoding.forum.service.config.repository.params.SearchConfigParams;
import com.github.paicoding.forum.service.config.service.ConfigSettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Banner后台接口
 * */
@Service
public class ConfigSettingServiceImpl implements ConfigSettingService {
    

    @Override
    public void saveConfig(ConfigReq configReq) {}

    @Override
    public void deleteConfig(Integer configId) {}

    @Override
    public void operateConfig(Integer configId, Integer pushStatus) {}

    @Override
    public PageVo<ConfigDTO> getConfigList(SearchConfigReq req) { return null; }

    @Override
    public PageVo<ConfigDTO> getNoticeList(PageParam pageParam) { return null; }
}
