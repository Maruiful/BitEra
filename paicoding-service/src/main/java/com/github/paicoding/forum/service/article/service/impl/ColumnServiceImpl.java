package com.github.paicoding.forum.service.article.service.impl;

import com.github.paicoding.forum.api.model.exception.ExceptionUtil;
import com.github.paicoding.forum.api.model.vo.PageListVo;
import com.github.paicoding.forum.api.model.vo.PageParam;
import com.github.paicoding.forum.api.model.vo.article.dto.ColumnDTO;
import com.github.paicoding.forum.api.model.vo.article.dto.SimpleArticleDTO;
import com.github.paicoding.forum.api.model.vo.constants.StatusEnum;
import com.github.paicoding.forum.api.model.vo.user.dto.BaseUserInfoDTO;
import com.github.paicoding.forum.api.model.vo.user.dto.ColumnFootCountDTO;
import com.github.paicoding.forum.service.article.conveter.ColumnConvert;
import com.github.paicoding.forum.service.article.repository.dao.ArticleDao;
import com.github.paicoding.forum.service.article.repository.dao.ColumnArticleDao;
import com.github.paicoding.forum.service.article.repository.dao.ColumnDao;
import com.github.paicoding.forum.service.article.repository.entity.ColumnArticleDO;
import com.github.paicoding.forum.service.article.repository.entity.ColumnInfoDO;
import com.github.paicoding.forum.service.article.service.ColumnService;
import com.github.paicoding.forum.service.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

/** */
@Service
public class ColumnServiceImpl implements ColumnService {
    

    @Override
    public ColumnArticleDO getColumnArticleRelation(Long articleId) { return null; }

    @Override
    public PageListVo<ColumnDTO> listColumn(PageParam pageParam) { return null; }

    @Override
    public ColumnDTO queryBasicColumnInfo(Long columnId) { return null; }

    @Override
    public ColumnDTO queryColumnInfo(Long columnId) { return null; }

    @Override
    public ColumnArticleDO queryColumnArticle(long columnId, Integer section) { return null; }

    @Override
    public List<SimpleArticleDTO> queryColumnArticles(long columnId) { return null; }

    @Override
    public Long getTutorialCount() { return null; }
}
