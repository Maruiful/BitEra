package com.github.paicoding.forum.service.chatai.service.impl.chatgpt;

import com.github.paicoding.forum.api.model.enums.ChatAnswerTypeEnum;
import com.github.paicoding.forum.api.model.enums.ai.AISourceEnum;
import com.github.paicoding.forum.api.model.enums.ai.AiChatStatEnum;
import com.github.paicoding.forum.api.model.vo.chat.ChatItemVo;
import com.github.paicoding.forum.api.model.vo.chat.ChatRecordsVo;
import com.github.paicoding.forum.service.chatai.service.AbsChatService;
import com.plexpt.chatgpt.listener.AbstractStreamListener;
import lombok.extern.slf4j.Slf4j;
import okhttp3.sse.EventSource;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.function.BiConsumer;

/** */
@Slf4j
@Service
public class ChatGptAiServiceImpl extends AbsChatService {
    @Autowired
    private ChatGptIntegration chatGptIntegration;

    @Override
    public AiChatStatEnum doAnswer(Long user, ChatItemVo chat)  { return null; }

    @Override
    public AiChatStatEnum doAsyncAnswer(Long user, ChatRecordsVo chatRes, BiConsumer<AiChatStatEnum, ChatRecordsVo> consumer) {
        ChatItemVo item = chatRes.getRecords().get(0);
        AbstractStreamListener listener = new AbstractStreamListener() {
            @Override
            public void onMsg(String message) {
                // 成功返回结果的场景
                if (StringUtils.isNotBlank(message)) {
                    item.appendAnswer(message);
                    consumer.accept(AiChatStatEnum.MID, chatRes);
                    if (log.isDebugEnabled()) {
                        log.debug("ChatGpt返回内容: {}", lastMessage);
                    }
                }
            }

            @Override
            public void onClosed(EventSource eventSource) {
                super.onClosed(eventSource);
                // 检查是否正常结束对话
                if (item.getAnswerType() != ChatAnswerTypeEnum.STREAM_END) {
                    // 主动结束这一次的对话
                    if (StringUtils.isBlank(lastMessage)) {
                        item.appendAnswer("大模型超时未返回结果，主动关闭会话；请重新提问吧\n").setAnswerType(ChatAnswerTypeEnum.STREAM_END);
                        consumer.accept(AiChatStatEnum.ERROR, chatRes);
                    } else {
                        item.appendAnswer("\n").setAnswerType(ChatAnswerTypeEnum.STREAM_END);
                        consumer.accept(AiChatStatEnum.END, chatRes);
                    }
                }
            }

            @Override
            public void onError(Throwable throwable, String response)  {}
        };

        // 注册回答结束的回调钩子
        listener.setOnComplate((s) -> {
            item.appendAnswer("\n")
                    .setAnswerType(ChatAnswerTypeEnum.STREAM_END);
            consumer.accept(AiChatStatEnum.END, chatRes);
        });
        chatGptIntegration.streamReturn(user, chatRes.getRecords(), listener);
        return AiChatStatEnum.IGNORE;
    }

    @Override
    public AISourceEnum source()  { return null; }

    @Override
    public boolean asyncFirst()  { return false; }
}
