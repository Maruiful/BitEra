package com.github.paicoding.forum.web.front.chat.stomp;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.server.HandshakeHandler;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

/**
 * v1.1 stomp协议的websocket实现的chatgpt聊天方式
 * */
@Slf4j
@Configuration
@EnableWebSocketMessageBroker // 开启websocket代理
public class WsChatConfig implements WebSocketMessageBrokerConfigurer {
    /**
     * 这里定义的是客户端接收服务端消息的相关信息，如派聪明的回答： WsAnswerHelper#response 就是往 "/chat/rsp" 发送消息
     * 对应的前端订阅的也是 chat/index.html: stompClient.subscribe(`/user/chat/rsp`, xxx)
     *
     * @param config
     */
    @Override
    public void configureMessageBroker(MessageBrokerRegistry config)  {}


    /**
     * 添加一个服务端点，来接收客户端的连接
     * 即客户端创建ws时，指定的地址, chat/index.html: let socket = new WebSocket(`${protocol}//${host}/gpt/${session}/${aiType}`);
     * @param registry
     */
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry)  {}

    /**
     * 配置接收消息的拦截器
     *
     * 设置输出消息通道的线程数，默认线程为1，可以自己自定义线程数，最大线程数，线程存活时间
     *
     * @param registration
     */
    @Override
    public void configureClientInboundChannel(ChannelRegistration registration)  {}

    /**
     * 配置返回消息的拦截器
     *
     * @param registration
     */
    @Override
    public void configureClientOutboundChannel(ChannelRegistration registration)  {}

    @Bean
    public HandshakeHandler handshakeHandler()  { return null; }

    @Bean
    public HttpSessionHandshakeInterceptor handshakeInterceptor()  { return null; }

    @Bean
    public ChannelInterceptor channelInInterceptor()  { return null; }

    @Bean
    public ChannelInterceptor channelOutInterceptor()  { return null; }
}
