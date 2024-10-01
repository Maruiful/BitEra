package com.github.paicoding.forum.service.sitemap.service.impl;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.github.paicoding.forum.api.model.enums.ArticleEventEnum;
import com.github.paicoding.forum.api.model.event.ArticleMsgEvent;
import com.github.paicoding.forum.api.model.vo.article.dto.SimpleArticleDTO;
import com.github.paicoding.forum.core.cache.RedisClient;
import com.github.paicoding.forum.core.util.DateUtil;
import com.github.paicoding.forum.service.article.repository.dao.ArticleDao;
import com.github.paicoding.forum.service.article.repository.entity.ArticleDO;
import com.github.paicoding.forum.service.sitemap.constants.SitemapConstants;
import com.github.paicoding.forum.service.sitemap.model.SiteCntVo;
import com.github.paicoding.forum.service.sitemap.model.SiteMapVo;
import com.github.paicoding.forum.service.sitemap.model.SiteUrlVo;
import com.github.paicoding.forum.service.sitemap.service.SitemapService;
import com.github.paicoding.forum.service.statistics.service.CountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/** */
@Slf4j
@Service
public class SitemapServiceImpl implements SitemapService {
    

    @Resource
    

    
    public SiteMapVo getSiteMap() { return null; }

    @Override
    public void refreshSitemap() {}

    

    
    public void autoUpdateSiteMap(ArticleMsgEvent<ArticleDO> event) {}

    

    
    public void autoRefreshCache() {}

    @Override
    public void saveVisitInfo(String visitIp, String path) {}

    @Override
    public SiteCntVo querySiteVisitInfo(LocalDate date, String path) { return null; }
}
