package com.github.paicoding.forum.web.front.home.helper;

import com.github.paicoding.forum.api.model.context.ReqInfoContext;
import com.github.paicoding.forum.api.model.enums.ConfigTypeEnum;
import com.github.paicoding.forum.api.model.vo.PageListVo;
import com.github.paicoding.forum.api.model.vo.PageParam;
import com.github.paicoding.forum.api.model.vo.article.dto.ArticleDTO;
import com.github.paicoding.forum.api.model.vo.article.dto.CategoryDTO;
import com.github.paicoding.forum.api.model.vo.banner.dto.ConfigDTO;
import com.github.paicoding.forum.api.model.vo.recommend.CarouseDTO;
import com.github.paicoding.forum.api.model.vo.user.dto.UserStatisticInfoDTO;
import com.github.paicoding.forum.core.async.AsyncUtil;
import com.github.paicoding.forum.core.common.CommonConstants;
import com.github.paicoding.forum.service.article.service.ArticleReadService;
import com.github.paicoding.forum.service.article.service.CategoryService;
import com.github.paicoding.forum.service.config.service.ConfigService;
import com.github.paicoding.forum.service.sidebar.service.SidebarService;
import com.github.paicoding.forum.service.user.service.UserService;
import com.github.paicoding.forum.web.front.home.vo.IndexVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

/**
 * 首页推荐相关
 * */
@Component
public class IndexRecommendHelper {
    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ArticleReadService articleService;

    @Autowired
    private UserService userService;

    @Autowired
    private SidebarService sidebarService;

    @Autowired
    private ConfigService configService;

    public IndexVo buildIndexVo(String activeTab)  { return null; }

    public IndexVo buildSearchVo(String key)  { return null; }

    /**
     * 轮播图
     *
     * @return
     */
    private List<CarouseDTO> homeCarouselList()  { return null; }

    /**
     * 文章列表
     */
    private PageListVo<ArticleDTO> articleList(Long categoryId)  { return null; }

    /**
     * 置顶top 文章列表
     */
    private List<ArticleDTO> topArticleList(CategoryDTO category)  { return null; }

    /**
     * 返回分类列表
     *
     * @param active 选中的分类
     * @param vo     返回结果
     * @return 返回选中的分类；当没有匹配时，返回默认的全部分类
     */
    private CategoryDTO categories(String active, IndexVo vo) {
        List<CategoryDTO> allList = categoryService.loadAllCategories();
        // 查询所有分类的对应的文章数
        Map<Long, Long> articleCnt = articleService.queryArticleCountsByCategory();
        // 过滤掉文章数为0的分类
        allList.removeIf(c -> articleCnt.getOrDefault(c.getCategoryId(), 0L) <= 0L);

        // 刷新选中的分类
        AtomicReference<CategoryDTO> selectedArticle = new AtomicReference<>();
        allList.forEach(category -> {
            if (category.getCategory().equalsIgnoreCase(active)) {
                category.setSelected(true);
                selectedArticle.set(category);
            } else {
                category.setSelected(false);
            }
        });

        // 添加默认的全部分类
        allList.add(0, new CategoryDTO(0L, CategoryDTO.DEFAULT_TOTAL_CATEGORY));
        if (selectedArticle.get() == null) {
            selectedArticle.set(allList.get(0));
            allList.get(0).setSelected(true);
        }

        vo.setCategories(allList);
        return selectedArticle.get();
    }


    private UserStatisticInfoDTO loginInfo()  { return null; }
}
