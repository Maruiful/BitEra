package com.github.paicoding.forum.service.notify.service.impl;

import com.github.paicoding.forum.api.model.context.ReqInfoContext;
import com.github.paicoding.forum.api.model.enums.NotifyStatEnum;
import com.github.paicoding.forum.api.model.enums.NotifyTypeEnum;
import com.github.paicoding.forum.api.model.vo.PageListVo;
import com.github.paicoding.forum.api.model.vo.PageParam;
import com.github.paicoding.forum.api.model.vo.notify.dto.NotifyMsgDTO;
import com.github.paicoding.forum.core.util.NumUtil;
import com.github.paicoding.forum.core.ws.WebSocketResponseUtil;
import com.github.paicoding.forum.service.notify.repository.dao.NotifyMsgDao;
import com.github.paicoding.forum.service.notify.repository.entity.NotifyMsgDO;
import com.github.paicoding.forum.service.notify.service.NotifyService;
import com.github.paicoding.forum.service.user.repository.entity.UserFootDO;
import com.github.paicoding.forum.service.user.service.UserRelationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Service;
import java.util.*;

/**
 * 消息通知服务实现
 */
@Slf4j
@Service
public class NotifyServiceImpl implements NotifyService {

   
    public Set<String> load(Long aLong) throws Exception { return null; }

    @Override
    public int queryUserNotifyMsgCount(Long userId) { return 0; }

    @Override
    public PageListVo<NotifyMsgDTO> queryUserNotices(Long userId, NotifyTypeEnum type, PageParam page) { return null; }

    @Override
    public Map<String, Integer> queryUnreadCounts(long userId) { return null; }

    @Override
    public void saveArticleNotify(UserFootDO foot, NotifyTypeEnum notifyTypeEnum) {}

    @Override
    public void notifyToUser(Long userId, String msg) {}

    @Override
    public void notifyChannelMaintain(StompHeaderAccessor accessor) {}
}