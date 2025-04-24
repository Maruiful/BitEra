package com.github.paicoding.forum.api.model.vo.chat;

import lombok.Data;

import java.io.Serializable;

@Data
public class ChatSessionItemVo implements Serializable {
    private static final long serialVersionUID = 4083274108548272765L;
    /**
     * 对话主题
     */
    private String title;

    /**
     * 对话id，用于确认聊天历史
     */
    private String chatId;

    /**
     * 首次提问时间
     */
    private Long creatTime;

    /**
     * 最后一次提问应答时间
     */
    private Long updateTime;

    /**
     * 问答次数
     */
    private int qasCnt;

}
