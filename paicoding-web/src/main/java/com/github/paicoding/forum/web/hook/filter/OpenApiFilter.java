package com.github.paicoding.forum.web.hook.filter;

import com.github.paicoding.forum.api.model.vo.ResVo;
import com.github.paicoding.forum.api.model.vo.constants.StatusEnum;
import com.github.paicoding.forum.core.util.IpUtil;
import com.github.paicoding.forum.core.util.JsonUtil;
import com.github.paicoding.forum.web.openapi.config.OpenApiProperties;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 1. 请求参数日志输出过滤器
 * 2. 判断用户是否登录
 * */
@Slf4j
@Order(Integer.MIN_VALUE)
@WebFilter(urlPatterns = "/openapi/*", filterName = "openApiFilter", asyncSupported = true)
public class OpenApiFilter implements Filter {
    /**
     * 用于身份校验的方式
     */
    private static final String OPEN_API_APP_ID = "pai-open-appid";

    @Autowired
    private OpenApiProperties openApiProperties;


    @Override
    public void init(FilterConfig filterConfig)  {}

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException  {}

    private boolean checkOpenApiAuth(HttpServletRequest request) {
        return false;
    }

    @Override
    public void destroy()  {}
}
