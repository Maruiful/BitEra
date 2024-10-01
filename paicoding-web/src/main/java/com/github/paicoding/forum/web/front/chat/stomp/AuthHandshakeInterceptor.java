package com.github.paicoding.forum.web.front.chat.stomp;

import com.github.paicoding.forum.api.model.context.ReqInfoContext;
import com.github.paicoding.forum.core.mdc.MdcUtil;
import com.github.paicoding.forum.core.mdc.SelfTraceIdGenerator;
import com.github.paicoding.forum.core.util.SessionUtil;
import com.github.paicoding.forum.core.util.SpringUtil;
import com.github.paicoding.forum.service.user.service.LoginService;
import com.github.paicoding.forum.web.front.chat.helper.WsAnswerHelper;
import com.github.paicoding.forum.web.global.GlobalInitService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

import java.util.Map;

/**
 * 握手拦截器, 用于身份验证识别
 * */
@Slf4j
public class AuthHandshakeInterceptor extends HttpSessionHandshakeInterceptor {

    /**
     * 握手前，进行用户身份校验识别
     *
     * @param request
     * @param response
     * @param wsHandler
     * @param attributes: 即对应的是Message中的 simpSessionAttributes 请求头
     * @return
     * @throws Exception
     */
    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Map<String, Object> attributes) throws Exception  { return false; }

    private String initAiSource(String path)  { return null; }

    @Override
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Exception ex)  {}
}
