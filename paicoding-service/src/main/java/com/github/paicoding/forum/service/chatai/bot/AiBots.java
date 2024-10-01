package com.github.paicoding.forum.service.chatai.bot;

import com.github.paicoding.forum.api.model.vo.chat.ChatItemVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

/** */
@Service
public class AiBots {
    @Autowired
    private HaterBot haterBot;

    /**
     * 判断目标用户是否为AI机器人
     *
     * @param userId
     * @return
     */
    public boolean aiBots(Long userId)  { return false; }

    /**
     * 自动补齐AI机器人的提示词
     *
     * @param userId
     * @return
     */
    public ChatItemVo autoBuildPrompt(Long userId)  { return null; }
}
