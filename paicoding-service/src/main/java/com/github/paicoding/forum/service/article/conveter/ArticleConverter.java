package com.github.paicoding.forum.service.article.conveter;

import com.github.paicoding.forum.api.model.vo.article.ArticlePostReq;
import com.github.paicoding.forum.api.model.vo.article.CategoryReq;
import com.github.paicoding.forum.api.model.vo.article.SearchArticleReq;
import com.github.paicoding.forum.api.model.vo.article.TagReq;
import com.github.paicoding.forum.api.model.vo.article.dto.ArticleDTO;
import com.github.paicoding.forum.api.model.vo.article.dto.CategoryDTO;
import com.github.paicoding.forum.api.model.vo.article.dto.TagDTO;
import com.github.paicoding.forum.service.article.repository.entity.ArticleDO;
import com.github.paicoding.forum.service.article.repository.entity.CategoryDO;
import com.github.paicoding.forum.service.article.repository.entity.TagDO;
import com.github.paicoding.forum.service.article.repository.params.SearchArticleParams;

import java.util.List;

/**
 * 文章转换
 * <p>
 * */
public class ArticleConverter {

    public static ArticleDO toArticleDo(ArticlePostReq req, Long author) { return null; }

    public static ArticleDTO toDto(ArticleDO articleDO) { return null; }

    public static List<ArticleDTO> toArticleDtoList(List<ArticleDO> articleDOS) { return null; }

    /**
     * do转换
     *
     * @param tag
     * @return
     */
    public static TagDTO toDto(TagDO tag) { return null; }

    public static List<TagDTO> toDtoList(List<TagDO> tags) { return null; }


    public static CategoryDTO toDto(CategoryDO category) { return null; }

    public static List<CategoryDTO> toCategoryDtoList(List<CategoryDO> categorys) { return null; }

    public static TagDO toDO(TagReq tagReq) { return null; }

    public static CategoryDO toDO(CategoryReq categoryReq) { return null; }

    public static SearchArticleParams toSearchParams(SearchArticleReq req) { return null; }
}