package com.github.paicoding.forum.service.config.service.impl;

import com.github.paicoding.forum.api.model.event.ConfigRefreshEvent;
import com.github.paicoding.forum.api.model.exception.ExceptionUtil;
import com.github.paicoding.forum.api.model.vo.PageVo;
import com.github.paicoding.forum.api.model.vo.config.GlobalConfigReq;
import com.github.paicoding.forum.api.model.vo.config.SearchGlobalConfigReq;
import com.github.paicoding.forum.api.model.vo.config.dto.GlobalConfigDTO;
import com.github.paicoding.forum.api.model.vo.constants.StatusEnum;
import com.github.paicoding.forum.core.senstive.SensitiveProperty;
import com.github.paicoding.forum.core.senstive.SensitiveService;
import com.github.paicoding.forum.core.util.NumUtil;
import com.github.paicoding.forum.core.util.SpringUtil;
import com.github.paicoding.forum.service.config.converter.ConfigStructMapper;
import com.github.paicoding.forum.service.config.repository.dao.ConfigDao;
import com.github.paicoding.forum.service.config.repository.entity.GlobalConfigDO;
import com.github.paicoding.forum.service.config.repository.params.SearchGlobalConfigParams;
import com.github.paicoding.forum.service.config.service.GlobalConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * 微信搜索「沉默王二」，回复 Java
 * */
@Service
public class GlobalConfigServiceImpl implements GlobalConfigService {
    

    @Override
    public PageVo<GlobalConfigDTO> getList(SearchGlobalConfigReq req) { return null; }

    @Override
    public void save(GlobalConfigReq req) {}

    @Override
    public void delete(Long id) {}

    @Override
    public void addSensitiveWhiteWord(String word) {}
}
