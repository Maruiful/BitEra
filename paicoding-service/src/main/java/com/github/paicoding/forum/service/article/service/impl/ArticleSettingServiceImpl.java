package com.github.paicoding.forum.service.article.service.impl;

import com.github.paicoding.forum.api.model.enums.OperateArticleEnum;
import com.github.paicoding.forum.api.model.vo.PageVo;
import com.github.paicoding.forum.api.model.vo.article.ArticlePostReq;
import com.github.paicoding.forum.api.model.vo.article.SearchArticleReq;
import com.github.paicoding.forum.api.model.vo.article.dto.ArticleAdminDTO;
import com.github.paicoding.forum.service.article.service.ArticleSettingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 文章设置服务实现
 */
@Slf4j
@Service
public class ArticleSettingServiceImpl implements ArticleSettingService {

    @Override
    public void updateArticle(ArticlePostReq req) {}

    @Override
    public PageVo<ArticleAdminDTO> getArticleList(SearchArticleReq req) { return null; }

    @Override
    public void deleteArticle(Long articleId) {}

    @Override
    public void operateArticle(Long articleId, OperateArticleEnum operate) {}
}