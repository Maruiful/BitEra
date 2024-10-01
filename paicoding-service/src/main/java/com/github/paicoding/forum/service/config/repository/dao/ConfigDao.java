package com.github.paicoding.forum.service.config.repository.dao;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.paicoding.forum.api.model.enums.ConfigTypeEnum;
import com.github.paicoding.forum.api.model.enums.PushStatusEnum;
import com.github.paicoding.forum.api.model.enums.YesOrNoEnum;
import com.github.paicoding.forum.api.model.vo.PageParam;
import com.github.paicoding.forum.api.model.vo.banner.dto.ConfigDTO;
import com.github.paicoding.forum.service.config.converter.ConfigConverter;
import com.github.paicoding.forum.service.config.converter.ConfigStructMapper;
import com.github.paicoding.forum.service.config.repository.entity.ConfigDO;
import com.github.paicoding.forum.service.config.repository.entity.GlobalConfigDO;
import com.github.paicoding.forum.service.config.repository.mapper.ConfigMapper;
import com.github.paicoding.forum.service.config.repository.mapper.GlobalConfigMapper;
import com.github.paicoding.forum.service.config.repository.params.SearchConfigParams;
import com.github.paicoding.forum.service.config.repository.params.SearchGlobalConfigParams;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/** */
@Repository
public class ConfigDao extends ServiceImpl<ConfigMapper, ConfigDO> {
    @Resource
    private GlobalConfigMapper globalConfigMapper;

    /**
     * 根据类型获取配置列表（无需分页）
     *
     * @param type
     * @return
     */
    public List<ConfigDTO> listConfigByType(Integer type)  { return null; }

    private LambdaQueryChainWrapper<ConfigDO> createConfigQuery(SearchConfigParams params)  { return null; }

    /**
     * 获取所有 Banner 列表（分页）
     *
     * @return
     */
    public List<ConfigDTO> listBanner(SearchConfigParams params)  { return null; }

    /**
     * 获取所有 Banner 总数（分页）
     *
     * @return
     */
    public Long countConfig(SearchConfigParams params)  { return null; }

    /**
     * 获取所有公告列表（分页）
     *
     * @return
     */
    public List<ConfigDTO> listNotice(PageParam pageParam)  { return null; }

    /**
     * 获取所有公告总数（分页）
     *
     * @return
     */
    public Integer countNotice()  { return null; }

    /**
     * 更新阅读相关计数
     */
    public void updatePdfConfigVisitNum(long configId, String extra)  {}

    public List<GlobalConfigDO> listGlobalConfig(SearchGlobalConfigParams params)  { return null; }

    public Long countGlobalConfig(SearchGlobalConfigParams params)  { return null; }

    private LambdaQueryWrapper<GlobalConfigDO> buildQuery(SearchGlobalConfigParams params)  { return null; }

    public void save(GlobalConfigDO globalConfigDO)  {}

    public void updateById(GlobalConfigDO globalConfigDO)  {}

    /**
     * 根据id查询全局配置
     *
     * @param id
     * @return
     */
    public GlobalConfigDO getGlobalConfigById(Long id)  { return null; }

    /**
     * 根据key查询全局配置
     *
     * @param key
     * @return
     */
    public GlobalConfigDO getGlobalConfigByKey(String key)  { return null; }

    public void delete(GlobalConfigDO globalConfigDO)  {}
}
