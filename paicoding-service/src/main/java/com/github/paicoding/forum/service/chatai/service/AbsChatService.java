package com.github.paicoding.forum.service.chatai.service;

import com.github.paicoding.forum.api.model.context.ReqInfoContext;
import com.github.paicoding.forum.api.model.enums.ai.AISourceEnum;
import com.github.paicoding.forum.api.model.enums.ai.AiChatStatEnum;
import com.github.paicoding.forum.api.model.vo.chat.ChatItemVo;
import com.github.paicoding.forum.api.model.vo.chat.ChatRecordsVo;
import com.github.paicoding.forum.core.cache.RedisClient;
import com.github.paicoding.forum.core.senstive.SensitiveService;
import com.github.paicoding.forum.core.util.SpringUtil;
import com.github.paicoding.forum.service.chatai.ChatFacade;
import com.github.paicoding.forum.service.chatai.bot.AiBots;
import com.github.paicoding.forum.service.chatai.constants.ChatConstants;
import com.github.paicoding.forum.service.user.service.UserAiService;
import com.google.common.collect.Sets;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

/**
 * 聊天的抽象模板类
 * */
@Slf4j
@Service
public abstract class AbsChatService implements ChatService {
    @Autowired
    private UserAiService userAiService;
    @Autowired
    private SensitiveService sensitiveService;
    @Autowired
    private ChatHistoryService chatHistoryService;

    @Value("${ai.maxNum.historyContextCnt:10}")
    protected Integer chatHistoryContextNum;


    /**
     * 查询已经使用的次数
     *
     * @param user
     * @return
     */
    protected int queryUserdCnt(Long user)  { return 0; }


    /**
     * 使用次数+1
     *
     * @param user
     * @return
     */
    protected Long incrCnt(Long user)  { return null; }

    /**
     * 保存聊天记录
     */
    protected void recordChatItem(Long user, ChatItemVo item)  {}

    /**
     * 查询用户的聊天历史
     *
     * @return
     */
    public ChatRecordsVo getChatHistory(Long user, AISourceEnum aiSource)  { return null; }

    @Override
    public ChatRecordsVo chat(Long user, String question)  { return null; }

    @Override
    public ChatRecordsVo chat(Long user, String question, Consumer<ChatRecordsVo> consumer)  { return null; }

    private ChatRecordsVo initResVo(Long user, String question)  { return null; }

    /**
     * 构建聊天上下文
     * 该方法旨在为用户构建一个聊天上下文，基于用户的聊天历史记录
     * 特别注意，对于多轮对话，我们仅取最近的十条记录作为上下文，如果聊天中存在提示词，则提示词之前的聊天全部丢掉
     *
     * @param user 用户ID，用于识别和获取特定用户的聊天历史
     * @return 返回一个包含聊天上下文的ChatItemVo对象列表
     */
    private List<ChatItemVo> buildChatContext(Long user) {
        // 用于多轮对话，我们这里只取最近的十条作为上下文传参
        List<ChatItemVo> history = chatHistoryService.listHistory(source(), user, ReqInfoContext.getReqInfo().getChatId(), chatHistoryContextNum);
        if (CollectionUtils.isEmpty(history) || history.size() == 1) {
            return history;
        }

        // 过滤掉提示词之前的消息
        Iterator<ChatItemVo> iterator = history.iterator();
        boolean toRemove = false;
        while (iterator.hasNext()) {
            ChatItemVo tmp = iterator.next();
            if (!toRemove) {
                if (tmp.getQuestion().startsWith(ChatConstants.PROMPT_TAG)) {
                    // 找到提示词，之后的全部删除
                    toRemove = true;
                }
            } else {
                iterator.remove();
            }
        }
        return history;
    }

    protected AiChatStatEnum answer(Long user, ChatRecordsVo res) {
        ChatItemVo itemVo = res.getRecords().get(0);
        AiChatStatEnum ans;
        List<String> sensitiveWords = sensitiveService.contains(itemVo.getQuestion());
        if (!CollectionUtils.isEmpty(sensitiveWords)) {
            itemVo.initAnswer(String.format(ChatConstants.SENSITIVE_QUESTION, sensitiveWords));
            ans = AiChatStatEnum.ERROR;
        } else {
            ans = doAnswer(user, itemVo);
            if (ans == AiChatStatEnum.END) {
                processAfterSuccessedAnswered(user, res);
            }
        }
        return ans;
    }

    /**
     * 提问，并将结果写入chat
     *
     * @param user
     * @param chat
     * @return true 表示正确回答了； false 表示回答出现异常
     */
    public abstract AiChatStatEnum doAnswer(Long user, ChatItemVo chat);

    /**
     * 成功返回之后的后置操作
     *
     * @param user
     * @param response
     */
    protected void processAfterSuccessedAnswered(Long user, ChatRecordsVo response)  {}

    /**
     * 异步聊天，即提问并不要求直接得到接口；等后台准备完毕之后再写入对应的结果
     *
     * @param user
     * @param question
     * @param consumer 执行成功之后，直接异步回调的通知
     * @return
     */
    @Override
    public ChatRecordsVo asyncChat(Long user, String question, Consumer<ChatRecordsVo> consumer) {
        ChatRecordsVo res = initResVo(user, question);
        if (!res.hasQaCnt()) {
            // 次数使用完毕
            consumer.accept(res);
            return res;
        }

        List<String> sensitiveWord = sensitiveService.contains(res.getRecords().get(0).getQuestion());
        if (!CollectionUtils.isEmpty(sensitiveWord) && !SpringUtil.getBean(AiBots.class).aiBots(user)) {
            // 机器人不进行敏感词校验
            // 包含敏感词的提问，直接返回异常
            res.getRecords().get(0).initAnswer(String.format(ChatConstants.SENSITIVE_QUESTION, sensitiveWord));
            consumer.accept(res);
        } else {
            final ChatRecordsVo newRes = res.clone();
            AiChatStatEnum needReturn = doAsyncAnswer(user, newRes, (ans, vo) -> {
                if (ans == AiChatStatEnum.END) {
                    // 只有最后一个会话，即ai的回答结束，才需要进行持久化，并计数
                    processAfterSuccessedAnswered(user, newRes);
                } else if (ans == AiChatStatEnum.ERROR) {
                    // 执行异常，更新AI模型
                    SpringUtil.getBean(ChatFacade.class).refreshAiSourceCache(Sets.newHashSet(source()));
                }
                // ai异步返回结果之后，我们将结果推送给前端用户
                consumer.accept(newRes);
            });

            if (needReturn.needResponse()) {
                // 异步响应时，为了避免长时间的等待，这里直接响应用户的提问，返回一个稍等得提示文案
                ChatItemVo nowItem = res.getRecords().get(0);
                nowItem.initAnswer(ChatConstants.ASYNC_CHAT_TIP);
                consumer.accept(res);
            }
        }
        return res;
    }

    /**
     * 异步返回结果
     *
     * @param user
     * @param response 保存提问 & 返回的结果，最终会返回给前端用户
     * @param consumer 具体将 response 写回前端的实现策略
     * @return 返回的会话状态，控制是否需要将结果直接返回给前端
     */
    public abstract AiChatStatEnum doAsyncAnswer(Long user, ChatRecordsVo response, BiConsumer<AiChatStatEnum, ChatRecordsVo> consumer);

    /**
     * 查询当前用户最多可提问的次数
     *
     * @param user
     * @return
     */
    protected int getMaxQaCnt(Long user)  { return 0; }
}
