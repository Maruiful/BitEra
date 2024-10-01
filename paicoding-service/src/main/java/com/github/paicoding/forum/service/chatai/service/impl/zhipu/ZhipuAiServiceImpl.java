package com.github.paicoding.forum.service.chatai.service.impl.zhipu;

import com.github.paicoding.forum.api.model.enums.ChatAnswerTypeEnum;
import com.github.paicoding.forum.api.model.enums.WsConnectStateEnum;
import com.github.paicoding.forum.api.model.enums.ai.AISourceEnum;
import com.github.paicoding.forum.api.model.enums.ai.AiChatStatEnum;
import com.github.paicoding.forum.api.model.vo.chat.ChatItemVo;
import com.github.paicoding.forum.api.model.vo.chat.ChatRecordsVo;
import com.github.paicoding.forum.service.chatai.service.AbsChatService;
import com.github.paicoding.forum.service.chatai.service.impl.xunfei.XunFeiAiServiceImpl;
import com.github.paicoding.forum.service.chatai.service.impl.xunfei.XunFeiIntegration;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.function.BiConsumer;

@Slf4j
@Service
public class ZhipuAiServiceImpl extends AbsChatService {

    @Autowired
    private ZhipuIntegration zhipuIntegration;

    @Override
    public AiChatStatEnum doAnswer(Long user, ChatItemVo chat)  { return null; }

    @Override
    public AiChatStatEnum doAsyncAnswer(Long user, ChatRecordsVo chatRes, BiConsumer<AiChatStatEnum, ChatRecordsVo> consumer)  { return null; }

    @Override
    public AISourceEnum source()  { return null; }

    @Override
    public boolean asyncFirst()  { return false; }

}
