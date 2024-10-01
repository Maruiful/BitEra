package com.github.paicoding.forum.service.config.service.impl;

import com.github.paicoding.forum.api.model.enums.ConfigTypeEnum;
import com.github.paicoding.forum.api.model.vo.banner.dto.ConfigDTO;
import com.github.paicoding.forum.service.config.repository.dao.ConfigDao;
import com.github.paicoding.forum.service.config.service.ConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Banner前台接口
 * */
@Service
public class ConfigServiceImpl implements ConfigService {
    

    @Override
    public List<ConfigDTO> getConfigList(ConfigTypeEnum configTypeEnum) { return null; }

    @Override
    public void updateVisit(long configId, String extra) {}
}
