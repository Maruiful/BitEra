package com.github.paicoding.forum.service.article.service.impl;

import com.github.paicoding.forum.api.model.vo.PageVo;
import com.github.paicoding.forum.api.model.vo.article.SearchTagReq;
import com.github.paicoding.forum.api.model.vo.article.TagReq;
import com.github.paicoding.forum.api.model.vo.article.dto.TagDTO;
import com.github.paicoding.forum.core.cache.RedisClient;
import com.github.paicoding.forum.core.util.JsonUtil;
import com.github.paicoding.forum.core.util.NumUtil;
import com.github.paicoding.forum.service.article.conveter.TagStructMapper;
import com.github.paicoding.forum.service.article.repository.dao.TagDao;
import com.github.paicoding.forum.service.article.repository.entity.TagDO;
import com.github.paicoding.forum.service.article.repository.params.SearchTagParams;
import com.github.paicoding.forum.service.article.service.TagSettingService;
import com.github.paicoding.forum.service.config.repository.dao.ConfigDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

/**
 * 标签后台接口
 * */
@Service
public class TagSettingServiceImpl implements TagSettingService {

    private static final String CACHE_TAG_PRE = "cache_tag_pre_";

    private static final Long CACHE_TAG_EXPRIE_TIME = 100L;

    @Autowired
    private TagDao tagDao;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveTag(TagReq tagReq) {
        TagDO tagDO = TagStructMapper.INSTANCE.toDO(tagReq);

        // 先写 MySQL
        if (NumUtil.nullOrZero(tagReq.getTagId())) {
            tagDao.save(tagDO);
        } else {
            tagDO.setId(tagReq.getTagId());
            tagDao.updateById(tagDO);
        }

        // 再删除 Redis
        String redisKey = CACHE_TAG_PRE + tagDO.getId();
        RedisClient.del(redisKey);
    }

    

    
    public void deleteTag(Integer tagId) {
        TagDO tagDO = tagDao.getById(tagId);
        if (tagDO != null){
            // 先写 MySQL
            tagDao.removeById(tagId);

            // 再删除 Redis
            String redisKey = CACHE_TAG_PRE + tagDO.getId();
            RedisClient.del(redisKey);
        }
    }

    @Override
    public void operateTag(Integer tagId, Integer pushStatus) {
        TagDO tagDO = tagDao.getById(tagId);
        if (tagDO != null){

            // 先写 MySQL
            tagDO.setStatus(pushStatus);
            tagDao.updateById(tagDO);

            // 再删除 Redis
            String redisKey = CACHE_TAG_PRE + tagDO.getId();
            RedisClient.del(redisKey);
        }
    }

    @Override
    public PageVo<TagDTO> getTagList(SearchTagReq req) {
        // 转换
        SearchTagParams params = TagStructMapper.INSTANCE.toSearchParams(req);
        // 查询
        List<TagDTO> tagDTOS = TagStructMapper.INSTANCE.toDTOs(tagDao.listTag(params));
        Long totalCount = tagDao.countTag(params);
        return PageVo.build(tagDTOS, params.getPageSize(), params.getPageNum(), totalCount);
    }

    @Override
    public TagDTO getTagById(Long tagId) { return null; }
}
