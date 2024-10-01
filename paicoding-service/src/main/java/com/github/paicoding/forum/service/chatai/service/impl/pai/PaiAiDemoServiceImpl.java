package com.github.paicoding.forum.service.chatai.service.impl.pai;

import com.github.paicoding.forum.api.model.enums.ChatAnswerTypeEnum;
import com.github.paicoding.forum.api.model.enums.ai.AISourceEnum;
import com.github.paicoding.forum.api.model.enums.ai.AiChatStatEnum;
import com.github.paicoding.forum.api.model.vo.chat.ChatItemVo;
import com.github.paicoding.forum.api.model.vo.chat.ChatRecordsVo;
import com.github.paicoding.forum.core.async.AsyncUtil;
import com.github.paicoding.forum.service.chatai.constants.ChatConstants;
import com.github.paicoding.forum.service.chatai.service.AbsChatService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.function.BiConsumer;

/**
 * 技术派价值一个亿的AI
 * */
@Service
public class PaiAiDemoServiceImpl extends AbsChatService {

    @Override
    public AISourceEnum source()  { return null; }

    @Override
    public AiChatStatEnum doAnswer(Long user, ChatItemVo chat)  { return null; }

    @Override
    public AiChatStatEnum doAsyncAnswer(Long user, ChatRecordsVo response, BiConsumer<AiChatStatEnum, ChatRecordsVo> consumer)  { return null; }

    private String qa(String q)  { return null; }

    @Override
    protected int getMaxQaCnt(Long user)  { return 0; }
}
