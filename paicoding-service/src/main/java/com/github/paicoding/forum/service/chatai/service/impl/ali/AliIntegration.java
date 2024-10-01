package com.github.paicoding.forum.service.chatai.service.impl.ali;

import cn.idev.excel.util.StringUtils;
import com.alibaba.dashscope.aigc.generation.Generation;
import com.alibaba.dashscope.aigc.generation.GenerationParam;
import com.alibaba.dashscope.aigc.generation.GenerationResult;
import com.alibaba.dashscope.common.Message;
import com.alibaba.dashscope.common.ResultCallback;
import com.alibaba.dashscope.common.Role;
import com.alibaba.dashscope.exception.ApiException;
import com.alibaba.dashscope.exception.InputRequiredException;
import com.alibaba.dashscope.exception.NoApiKeyException;
import com.alibaba.dashscope.utils.JsonUtils;
import com.github.paicoding.forum.api.model.enums.ChatAnswerTypeEnum;
import com.github.paicoding.forum.api.model.enums.ai.AiChatStatEnum;
import com.github.paicoding.forum.api.model.vo.chat.ChatItemVo;
import com.github.paicoding.forum.api.model.vo.chat.ChatRecordsVo;
import com.github.paicoding.forum.core.util.JsonUtil;
import com.github.paicoding.forum.service.chatai.constants.ChatConstants;
import com.zhipu.oapi.service.v4.model.ChatMessageAccumulator;
import com.zhipu.oapi.service.v4.model.ModelData;
import io.reactivex.Flowable;
import lombok.Data;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.function.BiConsumer;

@Slf4j
@Setter
@Component
public class AliIntegration {
    @Autowired
    private AliConfig config;

    public void streamReturn(Long user, ChatRecordsVo chatRecord, BiConsumer<AiChatStatEnum, ChatRecordsVo> callback) {
        try {
            ChatItemVo item = chatRecord.getRecords().get(0);

            Generation gen = new Generation();
            // 支持上下文的多轮聊天
            List<Message> userMsgList = ChatConstants.toMsgList(chatRecord.getRecords(), this::toMsg);
            GenerationParam param = GenerationParam.builder()
                    .model(config.getModel())
                    .messages(userMsgList)
                    .resultFormat(GenerationParam.ResultFormat.MESSAGE)
                    .incrementalOutput(true)
                    .build();
            Semaphore semaphore = new Semaphore(0);
            StringBuilder fullContent = new StringBuilder();

            gen.streamCall(param, new ResultCallback<GenerationResult>() {
                @Override
                public void onEvent(GenerationResult message)  {}

                @Override
                public void onError(Exception err)  {}

                @Override
                public void onComplete()  {}
            });

            semaphore.acquire();
            log.info("Full content: \n{}", fullContent.toString());
        } catch (ApiException | NoApiKeyException | InputRequiredException | InterruptedException e) {
            log.error("An exception occurred: {}", e.getMessage());
        }
    }

    @Component
    @ConfigurationProperties(prefix = "ali")
    @Data
    public static class AliConfig {
        public String model;
    }

    public boolean directReturn(Long user, ChatItemVo chat) {
        Generation gen = new Generation();
        Message systemMsg = Message.builder()
                .role(Role.SYSTEM.getValue())
                .content("You are a helpful assistant.")
                .build();
        Message userMsg = Message.builder()
                .role(Role.USER.getValue())
                .content(chat.getQuestion())
                .build();
        GenerationParam param = GenerationParam.builder()
                .model(config.getModel())
                .messages(Arrays.asList(systemMsg, userMsg))
                .resultFormat(GenerationParam.ResultFormat.MESSAGE)
                .build();

        try {
            GenerationResult invokeModelApiResp = gen.call(param);

            chat.initAnswer(JsonUtil.toStr(invokeModelApiResp), ChatAnswerTypeEnum.JSON);
            log.info("阿里 AI 试用! 传参:{}, 返回:{}", chat, invokeModelApiResp);
        } catch (NoApiKeyException | InputRequiredException e) {
            throw new RuntimeException(e);
        }

        return true;
    }

    public static Flowable<ChatMessageAccumulator> mapStreamToAccumulator(Flowable<ModelData> flowable)  { return null; }

    private List<Message> toMsg(ChatItemVo item)  { return null; }
}
