package com.github.paicoding.forum.service.user.service.ai;

import com.github.paicoding.forum.api.model.context.ReqInfoContext;
import com.github.paicoding.forum.api.model.enums.ai.AISourceEnum;
import com.github.paicoding.forum.api.model.enums.user.UserAIStatEnum;
import com.github.paicoding.forum.api.model.enums.user.UserAiStrategyEnum;      
import com.github.paicoding.forum.api.model.vo.chat.ChatItemVo;
import com.github.paicoding.forum.api.model.vo.user.UserPwdLoginReq;
import com.github.paicoding.forum.service.chatai.bot.AiBots;
import com.github.paicoding.forum.service.user.converter.UserAiConverter;       
import com.github.paicoding.forum.service.user.repository.dao.UserAiDao;        
import com.github.paicoding.forum.service.user.repository.dao.UserAiHistoryDao; 
import com.github.paicoding.forum.service.user.repository.entity.UserAiDO;      
import com.github.paicoding.forum.service.user.repository.entity.UserAiHistoryDO;                                                                               
import com.github.paicoding.forum.service.user.service.UserAiService;
import com.github.paicoding.forum.service.user.service.conf.AiConfig;
import org.springframework.stereotype.Service;

/**
 * 用户AI服务实现
 */
@Service
public class UserAiServiceImpl implements UserAiService {

    @Override
    public void pushChatItem(AISourceEnum source, Long user, ChatItemVo item) {}

    @Override
    public int getMaxChatCnt(Long userId) { return 0; }

    @Override
    public void initOrUpdateAiInfo(UserPwdLoginReq loginReq) {}
}