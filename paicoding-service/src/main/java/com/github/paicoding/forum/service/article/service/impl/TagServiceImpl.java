package com.github.paicoding.forum.service.article.service.impl;

import com.github.paicoding.forum.api.model.vo.PageParam;
import com.github.paicoding.forum.api.model.vo.PageVo;
import com.github.paicoding.forum.api.model.vo.article.dto.TagDTO;
import com.github.paicoding.forum.service.article.repository.dao.TagDao;
import com.github.paicoding.forum.service.article.service.TagService;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * 标签Service
 * */
@Service
public class TagServiceImpl implements TagService {
    

    @Override
    public PageVo<TagDTO> queryTags(String key, PageParam pageParam) { return null; }

    @Override
    public Long queryTagId(String tag) { return null; }
}
