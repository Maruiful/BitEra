package com.github.paicoding.forum.web.front.chat.rest;

import cn.hutool.core.date.LocalDateTimeUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.paicoding.forum.api.model.context.ReqInfoContext;
import com.github.paicoding.forum.api.model.enums.ChatSocketStateEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * 基础的websocket实现通讯的方式
 * */
@Slf4j
public class SimpleChatgptHandler extends TextWebSocketHandler {

    // 返回 TextMessage
    private TextMessage getTextMessage(String msg, Integer type) throws JsonProcessingException  { return null; }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception  {}

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception  {}

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception  {}
}
