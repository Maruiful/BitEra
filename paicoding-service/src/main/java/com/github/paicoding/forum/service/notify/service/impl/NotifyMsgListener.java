package com.github.paicoding.forum.service.notify.service.impl;

import com.github.paicoding.forum.api.model.enums.DocumentTypeEnum;
import com.github.paicoding.forum.api.model.enums.NotifyStatEnum;
import com.github.paicoding.forum.api.model.enums.NotifyTypeEnum;
import com.github.paicoding.forum.api.model.enums.pay.PayStatusEnum;
import com.github.paicoding.forum.api.model.enums.pay.ThirdPayWayEnum;
import com.github.paicoding.forum.api.model.vo.notify.NotifyMsgEvent;
import com.github.paicoding.forum.api.model.vo.user.dto.BaseUserInfoDTO;
import com.github.paicoding.forum.core.util.SpringUtil;
import com.github.paicoding.forum.service.article.repository.entity.ArticleDO;
import com.github.paicoding.forum.service.article.repository.entity.ArticlePayRecordDO;
import com.github.paicoding.forum.service.article.service.ArticleReadService;
import com.github.paicoding.forum.service.comment.repository.entity.CommentDO;
import com.github.paicoding.forum.service.comment.service.CommentReadService;
import com.github.paicoding.forum.service.notify.repository.dao.NotifyMsgDao;
import com.github.paicoding.forum.service.notify.repository.entity.NotifyMsgDO;
import com.github.paicoding.forum.service.notify.service.NotifyService;
import com.github.paicoding.forum.service.user.repository.entity.UserFootDO;
import com.github.paicoding.forum.service.user.repository.entity.UserRelationDO;
import com.github.paicoding.forum.service.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * 消息通知监听器
 */
@Slf4j
@Async
@Service
public class NotifyMsgListener<T> implements ApplicationListener<NotifyMsgEvent<T>> {
    private static final Long ADMIN_ID = 1L;
    private final ArticleReadService articleReadService;
    private final CommentReadService commentReadService;
    private final NotifyMsgDao notifyMsgDao;
    private final NotifyService notifyService;
    private final UserService userService;

    public NotifyMsgListener(ArticleReadService articleReadService,
                             CommentReadService commentReadService,
                             NotifyService notifyService,
                             NotifyMsgDao notifyMsgDao,
                             UserService userService) {
        this.articleReadService = articleReadService;
        this.commentReadService = commentReadService;
        this.notifyService = notifyService;
        this.notifyMsgDao = notifyMsgDao;
        this.userService = userService;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void onApplicationEvent(NotifyMsgEvent<T> msgEvent) {}
}