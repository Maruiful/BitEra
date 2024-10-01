package com.github.paicoding.forum.service.article.service.impl;

import com.github.paicoding.forum.api.model.vo.PageVo;
import com.github.paicoding.forum.api.model.vo.article.CategoryReq;
import com.github.paicoding.forum.api.model.vo.article.SearchCategoryReq;
import com.github.paicoding.forum.api.model.vo.article.dto.CategoryDTO;
import com.github.paicoding.forum.core.util.NumUtil;
import com.github.paicoding.forum.service.article.conveter.ArticleConverter;
import com.github.paicoding.forum.service.article.conveter.CategoryStructMapper;
import com.github.paicoding.forum.service.article.repository.dao.CategoryDao;
import com.github.paicoding.forum.service.article.repository.entity.CategoryDO;
import com.github.paicoding.forum.service.article.repository.params.SearchCategoryParams;
import com.github.paicoding.forum.service.article.service.CategoryService;
import com.github.paicoding.forum.service.article.service.CategorySettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * 分类后台接口
 * */
@Service
public class CategorySettingServiceImpl implements CategorySettingService {
    

    @Override
    public void saveCategory(CategoryReq categoryReq) {}

    @Override
    public void deleteCategory(Integer categoryId) {}

    @Override
    public void operateCategory(Integer categoryId, Integer pushStatus) {}

    @Override
    public PageVo<CategoryDTO> getCategoryList(SearchCategoryReq req) { return null; }
}
