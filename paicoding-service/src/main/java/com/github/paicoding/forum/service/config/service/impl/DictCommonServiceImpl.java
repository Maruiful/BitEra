package com.github.paicoding.forum.service.config.service.impl;

import com.github.paicoding.forum.api.model.vo.article.dto.CategoryDTO;
import com.github.paicoding.forum.api.model.vo.article.dto.DictCommonDTO;
import com.github.paicoding.forum.service.article.service.CategoryService;
import com.github.paicoding.forum.service.config.repository.dao.DictCommonDao;
import com.github.paicoding.forum.service.config.service.DictCommonService;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 字典Service
 * */
@Service
public class DictCommonServiceImpl implements DictCommonService {
    

    @Override
    public Map<String, Object> getDict() { return null; }
}
