package com.github.paicoding.forum.service.article.service;

import com.github.paicoding.forum.api.model.vo.PageParam;
import com.github.paicoding.forum.api.model.vo.PageVo;
import com.github.paicoding.forum.api.model.vo.article.dto.TagDTO;

public interface TagService {

    PageVo<TagDTO> queryTags(String key, PageParam pageParam);

    Long queryTagId(String tag);
}
