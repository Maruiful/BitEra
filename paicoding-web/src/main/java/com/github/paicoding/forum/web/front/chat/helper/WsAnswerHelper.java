package com.github.paicoding.forum.web.front.chat.helper;

import com.github.paicoding.forum.api.model.context.ReqInfoContext;
import com.github.paicoding.forum.api.model.enums.ai.AISourceEnum;
import com.github.paicoding.forum.api.model.vo.chat.ChatRecordsVo;
import com.github.paicoding.forum.core.mdc.MdcUtil;
import com.github.paicoding.forum.core.ws.WebSocketResponseUtil;
import com.github.paicoding.forum.service.chatai.ChatFacade;
import com.github.paicoding.forum.service.user.service.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import java.util.Map;

/** */
@Slf4j
@Component
public class WsAnswerHelper {
    public static final String AI_SOURCE_PARAM = "AI";

    @Autowired
    private ChatFacade chatFacade;

    private void sendMsgToUser(String session, String question)  {}

    public void sendMsgToUser(AISourceEnum ai, String session, String question) {
        if (ai == null) {
            // 自动选择AI类型
            sendMsgToUser(session, question);
        } else {
            ChatRecordsVo res = chatFacade.autoChat(ai, question, vo -> response(session, vo));
            log.info("AI直接返回：{}", res);
        }
    }

    public void sendMsgHistoryToUser(String session, AISourceEnum ai)  {}

    /**
     * 将返回结果推送给用户
     *
     * @param session
     * @param response
     */
    public void response(String session, ChatRecordsVo response)  {}

    public void execute(Map<String, Object> attributes, Runnable func)  {}
}
