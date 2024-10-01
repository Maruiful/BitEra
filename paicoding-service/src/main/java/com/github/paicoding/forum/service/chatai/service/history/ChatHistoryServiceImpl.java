package com.github.paicoding.forum.service.chatai.service.history;

import com.github.paicoding.forum.api.model.enums.ai.AISourceEnum;
import com.github.paicoding.forum.api.model.vo.chat.ChatItemVo;
import com.github.paicoding.forum.api.model.vo.chat.ChatSessionItemVo;
import com.github.paicoding.forum.core.cache.RedisClient;
import com.github.paicoding.forum.core.util.SpringUtil;
import com.github.paicoding.forum.service.chatai.bot.AiBots;
import com.github.paicoding.forum.service.chatai.bot.HaterBot;
import com.github.paicoding.forum.service.chatai.constants.ChatConstants;
import com.github.paicoding.forum.service.chatai.service.ChatHistoryService;
import com.github.paicoding.forum.service.user.service.UserAiService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * 对话历史记录
 */
@Service
public class ChatHistoryServiceImpl implements ChatHistoryService {

    @Override
    public List<ChatSessionItemVo> listChatSessions(AISourceEnum source, Long userId) { return null; }

    @Override
    public List<ChatItemVo> listHistory(AISourceEnum source, Long userId, String chatId, Integer size) { return null; }

    @Override
    public void saveRecord(AISourceEnum source, Long userId, String chatId, ChatItemVo item) {}

    @Override
    public Boolean updateChatSessionName(AISourceEnum source, String chatId, String title, Long userId) { return null; }

    @Override
    public Boolean removeChatSession(AISourceEnum source, String chatId, Long userId) { return null; }
}