package com.github.paicoding.forum.service.sidebar.service;

import com.github.paicoding.forum.api.model.enums.ConfigTypeEnum;
import com.github.paicoding.forum.api.model.enums.SidebarStyleEnum;
import com.github.paicoding.forum.api.model.enums.rank.ActivityRankTimeEnum;    
import com.github.paicoding.forum.api.model.vo.PageListVo;
import com.github.paicoding.forum.api.model.vo.PageParam;
import com.github.paicoding.forum.api.model.vo.article.dto.SimpleArticleDTO;    
import com.github.paicoding.forum.api.model.vo.banner.dto.ConfigDTO;
import com.github.paicoding.forum.api.model.vo.rank.dto.RankItemDTO;
import com.github.paicoding.forum.api.model.vo.recommend.RateVisitDTO;
import com.github.paicoding.forum.api.model.vo.recommend.SideBarDTO;
import com.github.paicoding.forum.api.model.vo.recommend.SideBarItemDTO;        
import com.github.paicoding.forum.core.util.JsonUtil;
import com.github.paicoding.forum.core.util.SpringUtil;
import com.github.paicoding.forum.service.article.repository.dao.ArticleDao;    
import com.github.paicoding.forum.service.article.service.ArticleReadService;   
import com.github.paicoding.forum.service.config.service.ConfigService;
import com.github.paicoding.forum.service.rank.service.UserActivityRankService;
import com.google.common.base.Splitter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 侧边栏服务实现
 */
@Service
public class SidebarServiceImpl implements SidebarService {

    @Autowired
    private ConfigService configService;

    @Autowired
    private ArticleReadService articleReadService;
    private UserActivityRankService userActivityRankService;

    @Override
    public List<SideBarDTO> queryHomeSidebarList() {
        List<SideBarDTO> list = new ArrayList<>();
        list.add(noticeSideBar());
        list.add(columnSideBar());
        list.add(hotArticles());
        SideBarDTO bar = rankList();
        if (bar != null) {
            list.add(bar);
        }
        return list;
    }

    /**
     * 公告信息
     *
     * @return
     */
    private SideBarDTO noticeSideBar() {
        List<ConfigDTO> noticeList = configService.getConfigList(ConfigTypeEnum.NOTICE);
        List<SideBarItemDTO> items = new ArrayList<>(noticeList.size());
        noticeList.forEach(configDTO -> {
            List<Integer> configTags;
            if (StringUtils.isBlank(configDTO.getTags())) {
                configTags = Collections.emptyList();
            } else {
                configTags = Splitter.on(",").splitToStream(configDTO.getTags()).map(s -> Integer.parseInt(s.trim())).collect(Collectors.toList());
            }
            items.add(new SideBarItemDTO()
                    .setName(configDTO.getName())
                    .setTitle(configDTO.getContent())
                    .setUrl(configDTO.getJumpUrl())
                    .setTime(configDTO.getCreateTime().getTime())
                    .setTags(configTags)
            );
        });
        return new SideBarDTO()
                .setTitle("关于技术派")
                // TODO 知识星球的
                .setImg("https://cdn.tobebetterjavaer.com/paicoding/main/paicoding-zsxq.jpg")
                .setUrl("https://paicoding.com/article/detail/2422000009961473")
                .setItems(items)
                .setStyle(SidebarStyleEnum.NOTICE.getStyle());
    }


    /**
     * 推荐教程的侧边栏
     *
     * @return
     */
    private SideBarDTO columnSideBar() {
        List<ConfigDTO> columnList = configService.getConfigList(ConfigTypeEnum.COLUMN);
        List<SideBarItemDTO> items = new ArrayList<>(columnList.size());
        columnList.forEach(configDTO -> {
            SideBarItemDTO item = new SideBarItemDTO();
            item.setName(configDTO.getName());
            item.setTitle(configDTO.getContent());
            item.setUrl(configDTO.getJumpUrl());
            item.setImg(configDTO.getBannerUrl());
            items.add(item);
        });
        return new SideBarDTO().setTitle("精选教程").setItems(items).setStyle(SidebarStyleEnum.COLUMN.getStyle());
    }


    /**
     * 热门文章
     *
     * @return
     */
    private SideBarDTO hotArticles() {
        PageListVo<SimpleArticleDTO> vo = articleReadService.queryHotArticlesForRecommend(PageParam.newPageInstance(1, 8));
        List<SideBarItemDTO> items = vo.getList().stream().map(s -> new SideBarItemDTO().setTitle(s.getTitle()).setUrl("/article/detail/" + s.getId()).setTime(s.getCreateTime().getTime())).collect(Collectors.toList());
        return new SideBarDTO().setTitle("热门文章").setItems(items).setStyle(SidebarStyleEnum.ARTICLES.getStyle());
    }

    /**
     * 排行榜
     *
     * @return
     */
    private SideBarDTO rankList() {
        List<RankItemDTO> list = userActivityRankService.queryRankList(ActivityRankTimeEnum.MONTH, 8);
        if (list.isEmpty()) {
            return null;
        }
        SideBarDTO sidebar = new SideBarDTO().setTitle("月度活跃排行榜").setStyle(SidebarStyleEnum.ACTIVITY_RANK.getStyle());
        List<SideBarItemDTO> itemList = new ArrayList<>();
        for (RankItemDTO item : list) {
            SideBarItemDTO sideItem = new SideBarItemDTO().setName(item.getUser().getName())
                    .setUrl(String.valueOf(item.getUser().getUserId()))
                    .setImg(item.getUser().getAvatar())
                    .setTime(item.getScore().longValue());
            itemList.add(sideItem);
        }
        sidebar.setItems(itemList);
        return sidebar;
    }

    @Override
    public List<SideBarDTO> queryArticleDetailSidebarList(Long author, Long articleId) {
        List<SideBarDTO> list = new ArrayList<>(2);
        // 不能直接使用 pdfSideBar()的方式调用，会导致缓存不生效
        list.add(SpringUtil.getBean(SidebarServiceImpl.class).pdfSideBar());
        list.add(recommendByAuthor(author, articleId, PageParam.DEFAULT_PAGE_SIZE));
        return list;
    }

    
    public SideBarDTO pdfSideBar() { return null; }

    
    public SideBarDTO recommendByAuthor(Long authorId, Long articleId, long size) { return null; }                                                              

    @Override
    public List<SideBarDTO> queryColumnSidebarList() {
        List<SideBarDTO> list = new ArrayList<>();
        list.add(subscribeSideBar());
        return list;
    }

    /**
     * 订阅公众号
     *
     * @return
     */
    private SideBarDTO subscribeSideBar() {
        return new SideBarDTO().setTitle("订阅").setSubTitle("楼仔")
                .setImg("//cdn.tobebetterjavaer.com/paicoding/a768cfc54f59d4a056f79d1c959dcae9.jpg")
                .setContent("10本校招必刷八股文")
                .setStyle(SidebarStyleEnum.SUBSCRIBE.getStyle());
    }
}