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
import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

/**
 * 用户AI服务实现
 */
@Service
public class UserAiServiceImpl implements UserAiService {

    private UserAiDao userAiDao;

    @Override
    public void pushChatItem(AISourceEnum source, Long user, ChatItemVo item) {}

    @Override
    public int getMaxChatCnt(Long userId) { return 0; }

    @Override
    public void initOrUpdateAiInfo(UserPwdLoginReq loginReq) {
        // 之前已经检查过编号是否已经被绑定过了，那我们直接进行绑定
        Long userId = loginReq.getUserId();
        UserAiDO userAiDO = userAiDao.getByUserId(userId);
        if (userAiDO == null) {
            // 初始化新的ai信息
            userAiDO = UserAiConverter.initAi(userId, loginReq.getStarNumber());
        } else if (StringUtils.isBlank(loginReq.getStarNumber()) && StringUtils.isBlank(loginReq.getInvitationCode())) {
            // 没有传递星球和邀请码时，直接返回，不用更新ai信息
            return;
        } else if (StringUtils.isNotBlank(loginReq.getStarNumber())) {
            // 之前有绑定信息，检查到与之前的不一致，则执行更新星球编号流程
            if (!Objects.equals(loginReq.getStarNumber(), userAiDO.getStarNumber())) {
                userAiDO.setStarNumber(loginReq.getStarNumber());
            }
            // 并设置为试用
            userAiDO.setState(UserAIStatEnum.TRYING.getCode());
            if (ReqInfoContext.getReqInfo().getUser() != null) {
                ReqInfoContext.getReqInfo().getUser().setStarStatus(UserAIStatEnum.TRYING);
            }
        }
        userAiDao.saveOrUpdateAiBindInfo(userAiDO, loginReq.getInvitationCode());
    }
}