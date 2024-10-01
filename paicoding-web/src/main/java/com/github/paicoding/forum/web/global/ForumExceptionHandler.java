package com.github.paicoding.forum.web.global;

import com.github.paicoding.forum.api.model.context.ReqInfoContext;
import com.github.paicoding.forum.api.model.exception.ForumException;
import com.github.paicoding.forum.api.model.vo.ResVo;
import com.github.paicoding.forum.api.model.vo.Status;
import com.github.paicoding.forum.api.model.vo.constants.StatusEnum;
import com.github.paicoding.forum.core.util.JsonUtil;
import com.github.paicoding.forum.core.util.SpringUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.core.NestedRuntimeException;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.messaging.handler.annotation.support.MethodArgumentTypeMismatchException;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.context.request.async.AsyncRequestTimeoutException;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 全局异常处理
 * fixme: 除了这种姿势之外，还可以使用 ControllerAdvice 注解方式
 * */
@Slf4j
@Order(-100)
public class ForumExceptionHandler implements HandlerExceptionResolver {

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
       return null;
    }

    private Status buildToastMsg(Exception ex) {
        return null;
    }

    private String getErrorPage(Status status, HttpServletResponse response)  { return null; }

    /**
     * 后台请求、api数据请求、上传图片等接口，返回json格式的异常提示信息
     * 其他异常，返回500的页面
     *
     * @param request
     * @param response
     * @return
     */
    private boolean restResponse(HttpServletRequest request, HttpServletResponse response)  { return false; }

    private boolean isAjaxRequest(HttpServletRequest request)  { return false; }

}
