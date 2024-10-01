package com.github.paicoding.forum.service.article.repository.dao;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.paicoding.forum.api.model.enums.PushStatusEnum;
import com.github.paicoding.forum.api.model.enums.YesOrNoEnum;
import com.github.paicoding.forum.api.model.vo.PageParam;
import com.github.paicoding.forum.api.model.vo.article.dto.TagDTO;
import com.github.paicoding.forum.service.article.conveter.ArticleConverter;
import com.github.paicoding.forum.service.article.repository.entity.TagDO;
import com.github.paicoding.forum.service.article.repository.mapper.TagMapper;
import com.github.paicoding.forum.service.article.repository.params.SearchTagParams;
import org.springframework.stereotype.Repository;

import java.util.List;

/** */
@Repository
public class TagDao extends ServiceImpl<TagMapper, TagDO> {

    /**
     * 获取已上线 Tags 列表（分页）
     *
     * @return
     */
    public List<TagDTO> listOnlineTag(String key, PageParam pageParam)  { return null; }

    /**
     * 获取已上线 Tags 总数（分页）
     *
     * @return
     */
    public Integer countOnlineTag(String key)  { return null; }

    private LambdaQueryChainWrapper<TagDO> createTagQuery(SearchTagParams params)  { return null; }

    /**
     * 获取所有 Tags 列表（分页）
     *
     * @return
     */
    public List<TagDO> listTag(SearchTagParams params)  { return null; }



    /**
     * 获取所有 Tags 总数（分页）
     *
     * @return
     */
    public Long countTag(SearchTagParams params)  { return null; }

    /**
     * 查询tagId
     *
     * @param tag
     * @return
     */
    public Long selectTagIdByTag(String tag)  { return null; }

    /**
     * 查询tag
     * @param tagId
     * @return
     */
    public TagDTO selectById(Long tagId)  { return null; }
}
