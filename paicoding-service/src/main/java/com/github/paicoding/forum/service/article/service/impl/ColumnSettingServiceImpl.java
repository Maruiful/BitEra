package com.github.paicoding.forum.service.article.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.IService;
import com.github.paicoding.forum.api.model.enums.YesOrNoEnum;
import com.github.paicoding.forum.api.model.enums.column.MovePositionEnum;
import com.github.paicoding.forum.api.model.exception.ExceptionUtil;
import com.github.paicoding.forum.api.model.vo.PageParam;
import com.github.paicoding.forum.api.model.vo.PageVo;
import com.github.paicoding.forum.api.model.vo.article.*;
import com.github.paicoding.forum.api.model.vo.article.dto.ColumnArticleDTO;
import com.github.paicoding.forum.api.model.vo.article.dto.ColumnArticleGroupDTO;
import com.github.paicoding.forum.api.model.vo.article.dto.ColumnDTO;
import com.github.paicoding.forum.api.model.vo.article.dto.SimpleColumnDTO;
import com.github.paicoding.forum.api.model.vo.constants.StatusEnum;
import com.github.paicoding.forum.api.model.vo.user.dto.BaseUserInfoDTO;
import com.github.paicoding.forum.core.util.NumUtil;
import com.github.paicoding.forum.service.article.conveter.ColumnArticleStructMapper;
import com.github.paicoding.forum.service.article.conveter.ColumnStructMapper;
import com.github.paicoding.forum.service.article.helper.TreeBuilder;
import com.github.paicoding.forum.service.article.repository.dao.ArticleDao;
import com.github.paicoding.forum.service.article.repository.dao.ColumnArticleDao;
import com.github.paicoding.forum.service.article.repository.dao.ColumnArticleGroupDao;
import com.github.paicoding.forum.service.article.repository.dao.ColumnDao;
import com.github.paicoding.forum.service.article.repository.entity.ArticleDO;
import com.github.paicoding.forum.service.article.repository.entity.ColumnArticleDO;
import com.github.paicoding.forum.service.article.repository.entity.ColumnArticleGroupDO;
import com.github.paicoding.forum.service.article.repository.entity.ColumnInfoDO;
import com.github.paicoding.forum.service.article.repository.params.SearchColumnArticleParams;
import com.github.paicoding.forum.service.article.repository.params.SearchColumnParams;
import com.github.paicoding.forum.service.article.service.ColumnSettingService;
import com.github.paicoding.forum.service.user.service.UserService;
import org.apache.http.util.Asserts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 专栏后台接口
 * */
@Service
public class ColumnSettingServiceImpl implements ColumnSettingService {


    @Autowired
    private ColumnArticleDao columnArticleDao;

    @Override
    public void saveColumn(ColumnReq req) {}

    

    
    public void saveColumnArticleGroup(ColumnArticleGroupReq req) {}

    

    
    public void saveColumnArticle(Long articleId, Long columnId) {
        // 转换参数
        // 插入的时候，需要判断是否已经存在
        ColumnArticleDO exist = columnArticleDao.getOne(Wrappers.<ColumnArticleDO>lambdaQuery()
                .eq(ColumnArticleDO::getArticleId, articleId));
        if (exist != null) {
            if (!Objects.equals(columnId, exist.getColumnId())) {
                // 更新
                exist.setColumnId(columnId);
                columnArticleDao.updateById(exist);
            }
        } else {
            // 将文章保存到专栏中，章节序号+1
            ColumnArticleDO columnArticleDO = new ColumnArticleDO();
            columnArticleDO.setColumnId(columnId);
            columnArticleDO.setArticleId(articleId);
            // section 自增+1
            Integer maxSection = columnArticleDao.selectMaxSection(columnId);
            columnArticleDO.setSection(maxSection + 1);
            columnArticleDao.save(columnArticleDO);
        }
    }

    

    
    public void saveColumnArticle(ColumnArticleReq req) {}

    @Override
    public void deleteColumn(Long columnId) {}

    

    
    public void deleteColumnArticle(Long id) {}

    

    
    public void sortColumnArticleApi(SortColumnArticleReq req) {}

    

    
    public void sortColumnArticleByIDApi(SortColumnArticleByIDReq req) {}

    @Override
    public PageVo<ColumnDTO> getColumnList(SearchColumnReq req) { return null; }

    @Override
    public PageVo<ColumnArticleDTO> getColumnArticleList(SearchColumnArticleReq req) { return null; }

    @Override
    public List<SimpleColumnDTO> listSimpleColumnBySearchKey(String key) { return null; }

    @Override
    public List<ColumnArticleGroupDTO> getColumnGroups(Long columnId) { return null; }

    

    


    public List<ColumnArticleGroupDTO> getColumnGroupAndArticles(Long columnId) { return null; }

    @Override
    public boolean deleteColumnGroup(Long groupId) { return false; }

    @Transactional
    public boolean moveColumnArticleOrGroup(MoveColumnArticleOrGroupReq req) { return false; }
}
