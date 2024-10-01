package com.github.paicoding.forum.service.article.service.impl;

import com.github.paicoding.forum.api.model.vo.article.dto.CategoryDTO;
import com.github.paicoding.forum.service.article.service.CategoryService;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * 类目Service
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    @Override
    public String queryCategoryName(Long categoryId) { return null; }

    @Override
    public List<CategoryDTO> loadAllCategories() { return null; }

    @Override
    public void refreshCache() {}

    @Override
    public Long queryCategoryId(String category) { return null; }
}