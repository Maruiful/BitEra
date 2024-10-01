package com.github.paicoding.forum.service.article.repository.dao;

import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.paicoding.forum.api.model.enums.PushStatusEnum;
import com.github.paicoding.forum.api.model.enums.YesOrNoEnum;
import com.github.paicoding.forum.api.model.vo.PageParam;
import com.github.paicoding.forum.api.model.vo.article.dto.CategoryDTO;
import com.github.paicoding.forum.service.article.conveter.CategoryStructMapper;
import com.github.paicoding.forum.service.article.repository.entity.CategoryDO;
import com.github.paicoding.forum.service.article.repository.mapper.CategoryMapper;
import com.github.paicoding.forum.service.article.repository.params.SearchCategoryParams;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 类目Service
 * */
@Repository
public class CategoryDao extends ServiceImpl<CategoryMapper, CategoryDO> {
    /**
     * @return
     */
    public List<CategoryDO> listAllCategoriesFromDb()  { return null; }

    // 抽一个私有方法，构造查询条件
    private LambdaQueryChainWrapper<CategoryDO> createCategoryQuery(SearchCategoryParams params)  { return null; }

    /**
     * 获取所有 Categorys 列表（分页）
     *
     * @return
     */
    public List<CategoryDTO> listCategory(SearchCategoryParams params)  { return null; }

    /**
     * 获取所有 Categorys 总数（分页）
     *
     * @return
     */
    public Long countCategory(SearchCategoryParams params)  { return null; }
}
