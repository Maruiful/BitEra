package com.github.paicoding.forum.web.hook.filter;

import cn.hutool.core.date.StopWatch;
import com.github.paicoding.forum.api.model.context.ReqInfoContext;
import com.github.paicoding.forum.core.async.AsyncUtil;
import com.github.paicoding.forum.core.mdc.MdcUtil;
import com.github.paicoding.forum.core.util.CrossUtil;
import com.github.paicoding.forum.core.util.EnvUtil;
import com.github.paicoding.forum.core.util.IpUtil;
import com.github.paicoding.forum.core.util.SessionUtil;
import com.github.paicoding.forum.core.util.SpringUtil;
import com.github.paicoding.forum.service.sitemap.service.impl.SitemapServiceImpl;
import com.github.paicoding.forum.service.statistics.service.StatisticsSettingService;
import com.github.paicoding.forum.service.user.service.LoginService;
import com.github.paicoding.forum.web.global.GlobalInitService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * 1. 请求参数日志输出过滤器
 * 2. 判断用户是否登录
 * */
@Slf4j
@WebFilter(urlPatterns = "/*", filterName = "reqRecordFilter", asyncSupported = true)
public class ReqRecordFilter implements Filter {
    private static Logger REQ_LOG = LoggerFactory.getLogger("req");
    /**
     * 返回给前端的traceId，用于日志追踪
     */
    private static final String GLOBAL_TRACE_ID_HEADER = "g-trace-id";

    @Autowired
    private GlobalInitService globalInitService;

    @Autowired
    private StatisticsSettingService statisticsSettingService;

    @Override
    public void init(FilterConfig filterConfig)  {}

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
       return;
    }

    @Override
    public void destroy()  {}

    private HttpServletRequest initReqInfo(HttpServletRequest request, HttpServletResponse response) {
        return request;
    }

    private void buildRequestLog(ReqInfoContext.ReqInfo req, HttpServletRequest request, long costTime)  {}


    private HttpServletRequest wrapperRequest(HttpServletRequest request, ReqInfoContext.ReqInfo reqInfo)  { return null; }

    private boolean isStaticURI(HttpServletRequest request)  { return false; }


    /**
     * 初始化设备id
     *
     * @return
     */
    private String getOrInitDeviceId(HttpServletRequest request, HttpServletResponse response) {
       return null;
    }
}
