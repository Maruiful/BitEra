package com.github.paicoding.forum.service.article.conveter;

import com.github.paicoding.forum.api.model.enums.ArticleReadTypeEnum;
import com.github.paicoding.forum.api.model.enums.ArticleTypeEnum;
import com.github.paicoding.forum.api.model.enums.YesOrNoEnum;
import com.github.paicoding.forum.api.model.enums.pay.ThirdPayWayEnum;
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
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 文章转换
 * <p>
 * */
public class ArticleConverter {

    public static ArticleDO toArticleDo(ArticlePostReq req, Long author) {
        ArticleDO article = new ArticleDO();
        // 设置作者ID
        article.setUserId(author);
        article.setId(req.getArticleId());
        article.setTitle(req.getTitle());
        article.setShortTitle(req.getShortTitle());
        article.setArticleType(ArticleTypeEnum.valueOf(req.getArticleType().toUpperCase()).getCode());
        article.setPicture(req.getCover() == null ? "" : req.getCover());
        article.setCategoryId(req.getCategoryId());
        article.setSource(req.getSource());
        article.setSourceUrl(req.getSourceUrl());
        article.setSummary(req.getSummary());
        article.setStatus(req.pushStatus().getCode());
        article.setDeleted(req.deleted() ? YesOrNoEnum.YES.getCode() : YesOrNoEnum.NO.getCode());
        article.setReadType(req.getReadType() == null ? ArticleReadTypeEnum.NORMAL.getType() : req.getReadType());
        if (article.getReadType().equals(ArticleReadTypeEnum.PAY_READ.getType())) {
            // 不指定价格时，默认0.99元
            article.setPayAmount(req.getPayAmount() == null ? 99 : req.getPayAmount());
            // 当不指定具体的支付方式时，统一使用native的扫码支付方式
            article.setPayWay(StringUtils.isBlank(req.getPayWay()) ? ThirdPayWayEnum.WX_NATIVE.getPay() : req.getPayWay());
        }
        return article;
    }

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