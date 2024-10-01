package com.github.paicoding.forum.web.global;

import com.github.paicoding.forum.api.model.context.ReqInfoContext;
import com.github.paicoding.forum.api.model.vo.article.dto.ColumnArticlesDTO;
import com.github.paicoding.forum.api.model.vo.article.dto.ColumnDTO;
import com.github.paicoding.forum.api.model.vo.article.dto.TagDTO;
import com.github.paicoding.forum.api.model.vo.seo.Seo;
import com.github.paicoding.forum.api.model.vo.seo.SeoTagVo;
import com.github.paicoding.forum.core.util.DateUtil;
import com.github.paicoding.forum.web.config.GlobalViewConfig;
import com.github.paicoding.forum.web.front.article.vo.ArticleDetailVo;
import com.github.paicoding.forum.web.front.user.vo.UserHomeVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * seo注入服务，下面加个页面使用
 * - 首页
 * - 文章详情页
 * - 用户主页
 * - 专栏内容详情页
 * <p>
 * ogp seo标签: <a href="https://ogp.me/">开放内容协议 OGP</a>
 * */
@Service
public class SeoInjectService {
    private static final String KEYWORDS = "技术派,开源社区,java,springboot,IT,程序员,开发者,mysql,redis,Java基础,多线程,JVM,虚拟机,数据库,MySQL,Spring,Redis,MyBatis,系统设计,分布式,RPC,高可用,高并发,沉默王二";
    private static final String DES = "技术派,一个基于 Spring Boot、MyBatis-Plus、MySQL、Redis、ElasticSearch、MongoDB、Docker、RabbitMQ 等技术栈实现的社区系统，采用主流的互联网技术架构、全新的UI设计、支持一键源码部署，拥有完整的文章&教程发布/搜索/评论/统计流程等，代码完全开源，没有任何二次封装，是一个非常适合二次开发/实战的现代化社区项目。学编程，就上技术派";

    @Resource
    private GlobalViewConfig globalViewConfig;

    /**
     * 文章详情页的seo标签
     *
     * @param detail
     */
    public void initColumnSeo(ArticleDetailVo detail)  {}

    /**
     * 教程详情seo标签
     *
     * @param detail
     */
    public void initColumnSeo(ColumnArticlesDTO detail, ColumnDTO column)  {}

    /**
     * 用户主页的seo标签
     *
     * @param user
     */
    public void initUserSeo(UserHomeVo user)  {}


    public Seo defaultSeo()  { return null; }

    private Seo initBasicSeoTag()  { return null; }

}
