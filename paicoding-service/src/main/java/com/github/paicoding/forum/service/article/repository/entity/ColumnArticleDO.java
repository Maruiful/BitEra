package com.github.paicoding.forum.service.article.repository.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.github.paicoding.forum.api.model.entity.BaseDO;
import com.github.paicoding.forum.api.model.enums.column.ColumnArticleReadEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 专栏文章
 * */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("column_article")
public class ColumnArticleDO extends BaseDO {
    private static final long serialVersionUID = -2372103913090667453L;

    /**
     * 专栏
     */
    private Long columnId;

    /**
     * 专栏文章
     */
    private Long articleId;

    /**
     * 专栏文章分组
     */
    private Long groupId;

    /**
     * 顺序，越小越靠前
     */
    private Integer section;

    /**
     * 专栏类型：免费、登录阅读、收费阅读等
     *
     * @see ColumnArticleReadEnum#getRead()
     */
    private Integer readType;
}
