package com.github.paicoding.forum.service.article.repository.dao;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.paicoding.forum.api.model.context.ReqInfoContext;
import com.github.paicoding.forum.api.model.enums.DocumentTypeEnum;
import com.github.paicoding.forum.api.model.enums.OfficalStatEnum;
import com.github.paicoding.forum.api.model.enums.PushStatusEnum;
import com.github.paicoding.forum.api.model.enums.YesOrNoEnum;
import com.github.paicoding.forum.api.model.vo.PageParam;
import com.github.paicoding.forum.api.model.vo.article.dto.ArticleAdminDTO;
import com.github.paicoding.forum.api.model.vo.article.dto.ArticleDTO;
import com.github.paicoding.forum.api.model.vo.article.dto.SimpleArticleDTO;
import com.github.paicoding.forum.api.model.vo.article.dto.YearArticleDTO;
import com.github.paicoding.forum.api.model.vo.user.dto.BaseUserInfoDTO;
import com.github.paicoding.forum.core.permission.UserRole;
import com.github.paicoding.forum.service.article.conveter.ArticleConverter;
import com.github.paicoding.forum.service.article.repository.entity.ArticleDO;
import com.github.paicoding.forum.service.article.repository.entity.ArticleDetailDO;
import com.github.paicoding.forum.service.article.repository.entity.ReadCountDO;
import com.github.paicoding.forum.service.article.repository.mapper.ArticleDetailMapper;
import com.github.paicoding.forum.service.article.repository.mapper.ArticleMapper;
import com.github.paicoding.forum.service.article.repository.mapper.ReadCountMapper;
import com.github.paicoding.forum.service.article.repository.params.SearchArticleParams;
import com.google.common.collect.Maps;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 文章相关DB操作
 * <p>
 * 多表结构的操作封装，只与DB操作相关
 * */
@Repository
public class ArticleDao extends ServiceImpl<ArticleMapper, ArticleDO> {
    @Resource
    private ArticleDetailMapper articleDetailMapper;
    @Resource
    private ReadCountMapper readCountMapper;
    @Resource
    private ArticleMapper articleMapper;


    /**
     * 查询文章详情
     *
     * @param articleId
     * @return
     */
    public ArticleDTO queryArticleDetail(Long articleId)  { return null; }

    /**
     * 判断展示审核中的字样，还是展示原文
     *
     * @param article 文章实体
     * @return false 表示需要展示审核中的字样 | true 表示展示原文
     */
    private boolean showReviewContent(ArticleDO article)  { return false; }


    // ------------ article content  ----------------

    private ArticleDetailDO findLatestDetail(long articleId)  {
        LambdaQueryWrapper<ArticleDetailDO> contentQuery = Wrappers.lambdaQuery();
        contentQuery.eq(ArticleDetailDO::getDeleted, YesOrNoEnum.NO.getCode())
                .eq(ArticleDetailDO::getArticleId, articleId)
                .orderByDesc(ArticleDetailDO::getVersion);
        return articleDetailMapper.selectList(contentQuery).get(0);
    }

    /**
     * 保存文章正文
     *
     * @param articleId
     * @param content
     * @return
     */
    public Long saveArticleContent(Long articleId, String content)  {
        ArticleDetailDO detail = new ArticleDetailDO();
        detail.setArticleId(articleId);
        detail.setContent(content);
        detail.setVersion(1L);
        articleDetailMapper.insert(detail);
        return detail.getId();
    }

    /**
     * 更正文章正文
     *
     * @param articleId
     * @param content
     * @param update    true 表示更新最后一条记录； false 表示新插入一个新的记录
     */
    public void updateArticleContent(Long articleId, String content, boolean update)  {
        if (update) {
            articleDetailMapper.updateContent(articleId, content);
        } else {
            ArticleDetailDO latest = findLatestDetail(articleId);
            latest.setVersion(latest.getVersion() + 1);
            latest.setId(null);
            latest.setContent(content);
            articleDetailMapper.insert(latest);
        }
    }

    // ------------- 文章列表查询 --------------

    public List<ArticleDO> listArticlesByUserId(Long userId, PageParam pageParam)  { return null; }


    public List<ArticleDO> listArticlesByCategoryId(Long categoryId, PageParam pageParam)  {
        if (categoryId != null && categoryId <= 0) {
            // 分类不存在时，表示查所有
            categoryId = null;
        }
        LambdaQueryWrapper<ArticleDO> query = Wrappers.lambdaQuery();
        query.eq(ArticleDO::getDeleted, YesOrNoEnum.NO.getCode())
                .eq(ArticleDO::getStatus, PushStatusEnum.ONLINE.getCode());

        // 如果分页中置顶的四条数据，需要加上官方的查询条件
        // 说明是查询官方的文章，非置顶的文章，只限制全部分类
        if (categoryId == null && pageParam.getPageSize() == PageParam.TOP_PAGE_SIZE) {
            query.eq(ArticleDO::getOfficalStat, OfficalStatEnum.OFFICAL.getCode());
        }

        Optional.ofNullable(categoryId).ifPresent(cid -> query.eq(ArticleDO::getCategoryId, cid));
        query.last(PageParam.getLimitSql(pageParam))
                .orderByDesc(ArticleDO::getToppingStat, ArticleDO::getCreateTime);
        return baseMapper.selectList(query);
    }

    public Long countArticleByCategoryId(Long categoryId)  { return null; }

    /**
     * 按照分类统计文章的数量
     *
     * @return key: categoryId, value: count
     */
    public Map<Long, Long> countArticleByCategoryId() {
        QueryWrapper<ArticleDO> query = Wrappers.query();
        query.select("category_id, count(*) as cnt")
                .eq("deleted", YesOrNoEnum.NO.getCode())
                .eq("status", PushStatusEnum.ONLINE.getCode()).groupBy("category_id");
        List<Map<String, Object>> mapList = baseMapper.selectMaps(query);
        Map<Long, Long> result = Maps.newHashMapWithExpectedSize(mapList.size());
        for (Map<String, Object> mp : mapList) {
            Long cnt = (Long) mp.get("cnt");
            if (cnt != null && cnt > 0) {
                result.put((Long) mp.get("category_id"), cnt);
            }
        }
        return result;
    }

    public List<ArticleDO> listArticlesByBySearchKey(String key, PageParam pageParam)  { return null; }

    /**
     * 通过关键词，从标题中找出相似的进行推荐，只返回主键 + 标题
     *
     * @param key
     * @return
     */
    public List<ArticleDO> listSimpleArticlesByBySearchKey(String key)  { return null; }


    /**
     * 阅读计数
     *
     * @param articleId
     * @return
     */
    public int incrReadCount(Long articleId)  {
        LambdaQueryWrapper<ReadCountDO> query = Wrappers.lambdaQuery();
        query.eq(ReadCountDO::getDocumentId, articleId).eq(ReadCountDO::getDocumentType, DocumentTypeEnum.ARTICLE.getCode());
        ReadCountDO record = readCountMapper.selectOne(query);
        if (record == null) {
            record = new ReadCountDO().setDocumentId(articleId).setDocumentType(DocumentTypeEnum.ARTICLE.getCode()).setCnt(1);
            readCountMapper.insert(record);
        } else {
            // fixme: 这里存在并发覆盖问题，推荐使用 update read_count set cnt = cnt + 1 where id = xxx
            record.setCnt(record.getCnt() + 1);
            readCountMapper.updateById(record);
        }
        return record.getCnt();
    }

    /**
     * 统计用户的文章计数
     *
     * @param userId
     * @return
     */
    public int countArticleByUser(Long userId)  { return 0; }


    /**
     * 热门文章推荐，适用于首页的侧边栏
     *
     * @param pageParam
     * @return
     */
    public List<SimpleArticleDTO> listHotArticles(PageParam pageParam)  { return null; }

    /**
     * 作者的热门文章推荐，适用于作者的详情页侧边栏
     *
     * @param userId
     * @param pageParam
     * @return
     */
    public List<SimpleArticleDTO> listAuthorHotArticles(long userId, PageParam pageParam)  { return null; }

    /**
     * 根据相同的类目 + 标签进行推荐
     *
     * @param categoryId
     * @param tagIds
     * @return
     */
    public List<ArticleDO> listRelatedArticlesOrderByReadCount(Long categoryId, List<Long> tagIds, PageParam pageParam)  { return null; }


    /**
     * 根据用户ID获取创作历程
     *
     * @param userId
     * @return
     */
    public List<YearArticleDTO> listYearArticleByUserId(Long userId)  { return null; }

    /**
     * 抽取样板代码
     */
    private LambdaQueryChainWrapper<ArticleDO> buildQuery(SearchArticleParams searchArticleParams)  { return null; }


    /**
     * 文章列表（用于后台）
     */
    public List<ArticleAdminDTO> listArticlesByParams(SearchArticleParams params)  { return null; }

    /**
     * 文章总数（用于后台）
     */
    public Long countArticleByParams(SearchArticleParams searchArticleParams)  { return null; }

    /**
     * 文章总数（用于后台）
     *
     * @return
     */
    public Long countArticle()  { return null; }

    public List<ArticleDO> selectByIds(List<Integer> ids)  { return null; }
}