package com.github.paicoding.forum.service.comment.service.impl;

import com.github.paicoding.forum.api.model.enums.NotifyTypeEnum;
import com.github.paicoding.forum.api.model.enums.YesOrNoEnum;
import com.github.paicoding.forum.api.model.enums.ai.AiBotEnum;
import com.github.paicoding.forum.api.model.exception.ExceptionUtil;
import com.github.paicoding.forum.api.model.vo.comment.CommentSaveReq;
import com.github.paicoding.forum.api.model.vo.constants.StatusEnum;
import com.github.paicoding.forum.api.model.vo.notify.NotifyMsgEvent;
import com.github.paicoding.forum.core.util.NumUtil;
import com.github.paicoding.forum.core.util.SpringUtil;
import com.github.paicoding.forum.service.article.repository.entity.ArticleDO;
import com.github.paicoding.forum.service.article.service.ArticleReadService;
import com.github.paicoding.forum.service.chatai.bot.HaterBot;
import com.github.paicoding.forum.service.comment.converter.CommentConverter;
import com.github.paicoding.forum.service.comment.repository.dao.CommentDao;
import com.github.paicoding.forum.service.comment.repository.entity.CommentDO;
import com.github.paicoding.forum.service.comment.service.CommentWriteService;
import com.github.paicoding.forum.service.user.service.UserFootService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Date;
import java.util.Objects;

/**
 * 评论Service
 * */
@Slf4j
@Service
public class CommentWriteServiceImpl implements CommentWriteService {
    

    @Autowired
    

    
    public Long saveComment(CommentSaveReq commentSaveReq) { return null; }

    

    
    public void deleteComment(Long commentId, Long userId) {}
}
