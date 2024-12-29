package com.github.paicoding.forum.service.article.service.impl;

import com.github.paicoding.forum.api.model.enums.YesOrNoEnum;
import com.github.paicoding.forum.api.model.vo.article.dto.CategoryDTO;
import com.github.paicoding.forum.service.article.conveter.ArticleConverter;
import com.github.paicoding.forum.service.article.repository.dao.CategoryDao;
import com.github.paicoding.forum.service.article.repository.entity.CategoryDO;
import com.github.paicoding.forum.service.article.service.CategoryService;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * 类目Service
 */
@Service
public class CategoryServiceImpl implements CategoryService {


    private LoadingCache<Long, CategoryDTO> categoryCaches;

    private CategoryDao categoryDao;
    @Override
    public String queryCategoryName(Long categoryId) {
        return categoryCaches.getUnchecked(categoryId).getCategory();
    }

    @Override
    public List<CategoryDTO> loadAllCategories() {
        if (categoryCaches.size() <= 5) {
            refreshCache();
        }
        List<CategoryDTO> list = new ArrayList<>(categoryCaches.asMap().values());
        list.removeIf(s -> s.getCategoryId() <= 0);
        list.sort(Comparator.comparingInt(CategoryDTO::getRank));
        return list;
    }

    @Override
    public void refreshCache() {}

    @Override
    public Long queryCategoryId(String category) { return null; }
}