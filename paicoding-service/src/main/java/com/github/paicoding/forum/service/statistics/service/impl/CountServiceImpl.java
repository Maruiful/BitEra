package com.github.paicoding.forum.service.statistics.service.impl;

import com.github.paicoding.forum.api.model.vo.user.dto.ArticleFootCountDTO;
import com.github.paicoding.forum.api.model.vo.user.dto.UserStatisticInfoDTO;
import com.github.paicoding.forum.core.cache.RedisClient;
import com.github.paicoding.forum.core.util.MapUtils;
import com.github.paicoding.forum.service.article.repository.dao.ArticleDao;
import com.github.paicoding.forum.service.comment.service.CommentReadService;
import com.github.paicoding.forum.service.statistics.constants.CountConstants;
import com.github.paicoding.forum.service.statistics.service.CountService;
import com.github.paicoding.forum.service.user.repository.dao.UserDao;
import com.github.paicoding.forum.service.user.repository.dao.UserFootDao;
import com.github.paicoding.forum.service.user.repository.dao.UserRelationDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 计数服务，后续计数相关的可以考虑基于redis来做
 * */
@Slf4j
@Service
public class CountServiceImpl implements CountService {
    

    @Override
    public ArticleFootCountDTO queryArticleCountInfoByArticleId(Long articleId) { return null; }

    @Override
    public ArticleFootCountDTO queryArticleCountInfoByUserId(Long userId) { return null; }

    @Override
    public Long queryCommentPraiseCount(Long commentId) { return null; }

    @Override
    public UserStatisticInfoDTO queryUserStatisticInfo(Long userId) { return null; }

    @Override
    public ArticleFootCountDTO queryArticleStatisticInfo(Long articleId) { return null; }

    @Override
    public void incrArticleReadCount(Long authorUserId, Long articleId) {}

    

    
    public void autoRefreshAllUserStatisticInfo() {}

    @Override
    public void refreshUserStatisticInfo(Long userId) {}

    

    


    public void refreshArticleStatisticInfo(Long articleId) {}
}
