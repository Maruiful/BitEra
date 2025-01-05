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
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 侧边栏服务实现
 */
@Service
public class SidebarServiceImpl implements SidebarService {

    @Override
    public List<SideBarDTO> queryHomeSidebarList() { return null; }

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