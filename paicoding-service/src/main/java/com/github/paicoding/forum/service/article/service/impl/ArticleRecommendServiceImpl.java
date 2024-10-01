package com.github.paicoding.forum.service.article.service.impl;

import com.github.paicoding.forum.api.model.vo.PageListVo;
import com.github.paicoding.forum.api.model.vo.PageParam;
import com.github.paicoding.forum.api.model.vo.article.dto.ArticleDTO;
import com.github.paicoding.forum.service.article.repository.dao.ArticleDao;
import com.github.paicoding.forum.service.article.repository.dao.ArticleTagDao;
import com.github.paicoding.forum.service.article.repository.entity.ArticleDO;
import com.github.paicoding.forum.service.article.repository.entity.ArticleTagDO;
import com.github.paicoding.forum.service.article.service.ArticleReadService;
import com.github.paicoding.forum.service.article.service.ArticleRecommendService;
import com.github.paicoding.forum.service.sidebar.service.SidebarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import java.util.List;
import java.util.stream.Collectors;

/** */
@Service
public class ArticleRecommendServiceImpl implements ArticleRecommendService {
    

    @Override
    public PageListVo<ArticleDTO> relatedRecommend(Long articleId, PageParam page) { return null; }
}
