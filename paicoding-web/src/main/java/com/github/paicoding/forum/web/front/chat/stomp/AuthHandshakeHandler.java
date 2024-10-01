package com.github.paicoding.forum.web.front.chat.stomp;

import com.github.paicoding.forum.api.model.context.ReqInfoContext;
import com.github.paicoding.forum.service.user.service.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;

import java.security.Principal;
import java.util.Map;

/**
 * 握手处理器
 * */
@Slf4j
public class AuthHandshakeHandler extends DefaultHandshakeHandler {

    @Override
    protected Principal determineUser(ServerHttpRequest request, WebSocketHandler wsHandler, Map<String, Object> attributes)  { return null; }
}
