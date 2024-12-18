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
import java.util.stream.Collectors;

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
    public static TagDTO toDto(TagDO tag) {
        if (tag == null) {
            return null;
        }
        TagDTO dto = new TagDTO();
        dto.setTag(tag.getTagName());
        dto.setTagId(tag.getId());
        dto.setStatus(tag.getStatus());
        return dto;
    }

    public static List<TagDTO> toDtoList(List<TagDO> tags) {
        return tags.stream().map(ArticleConverter::toDto).collect(Collectors.toList());
    }


    public static CategoryDTO toDto(CategoryDO category) { return null; }

    public static List<CategoryDTO> toCategoryDtoList(List<CategoryDO> categorys) { return null; }

    public static TagDO toDO(TagReq tagReq) { return null; }

    public static CategoryDO toDO(CategoryReq categoryReq) { return null; }

    public static SearchArticleParams toSearchParams(SearchArticleReq req) { return null; }
}