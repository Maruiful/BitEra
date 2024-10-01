package com.github.paicoding.forum.service.chatai.service.impl.chatgpt;

import com.github.paicoding.forum.api.model.context.ReqInfoContext;
import com.github.paicoding.forum.api.model.enums.ai.AISourceEnum;
import com.github.paicoding.forum.core.async.AsyncUtil;
import com.github.paicoding.forum.core.cache.RedisClient;
import com.github.paicoding.forum.service.chatai.constants.ChatConstants;
import com.github.paicoding.forum.service.chatai.service.ChatgptService;
import com.github.paicoding.forum.service.user.repository.entity.UserDO;
import com.github.paicoding.forum.service.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * chatgpt 服务：
 * <p>
 * 1. 同一个用户，只有50次的提问机会，采用redis计数
 * 2. 因为微信有5s的自动回复超时，因此需要做一个容错兼容，当执行超过3.5s就提前返回，将结果保存到内存中，等待下次交互再进行返回
 */
@Slf4j
@Service
public class ChatGptWxServiceImpl implements ChatgptService {

   
    public ChatRecordWxVo load(Long userId) { return null; }

    @Override
    public boolean inChat(String wxUuid, String content) { return false; }

    @Override
    public String chat(String wxUuid, String content) { return null; }
}