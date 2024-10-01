package com.github.paicoding.forum.service.article.service.impl;

import com.github.paicoding.forum.api.model.vo.PageVo;
import com.github.paicoding.forum.api.model.vo.article.SearchTagReq;
import com.github.paicoding.forum.api.model.vo.article.TagReq;
import com.github.paicoding.forum.api.model.vo.article.dto.TagDTO;
import com.github.paicoding.forum.core.cache.RedisClient;
import com.github.paicoding.forum.core.util.JsonUtil;
import com.github.paicoding.forum.core.util.NumUtil;
import com.github.paicoding.forum.service.article.conveter.TagStructMapper;
import com.github.paicoding.forum.service.article.repository.dao.TagDao;
import com.github.paicoding.forum.service.article.repository.entity.TagDO;
import com.github.paicoding.forum.service.article.repository.params.SearchTagParams;
import com.github.paicoding.forum.service.article.service.TagSettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

/**
 * 标签后台接口
 * */
@Service
public class TagSettingServiceImpl implements TagSettingService {
    

    @Autowired
    

    
    public void saveTag(TagReq tagReq) {}

    

    
    public void deleteTag(Integer tagId) {}

    @Override
    public void operateTag(Integer tagId, Integer pushStatus) {}

    @Override
    public PageVo<TagDTO> getTagList(SearchTagReq req) { return null; }

    @Override
    public TagDTO getTagById(Long tagId) { return null; }
}
