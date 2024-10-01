package com.github.paicoding.forum.service.article.service.impl;

import com.github.paicoding.forum.api.model.context.ReqInfoContext;
import com.github.paicoding.forum.api.model.enums.*;
import com.github.paicoding.forum.api.model.event.ArticleMsgEvent;
import com.github.paicoding.forum.api.model.exception.ExceptionUtil;
import com.github.paicoding.forum.api.model.vo.article.ArticlePostReq;
import com.github.paicoding.forum.api.model.vo.constants.StatusEnum;
import com.github.paicoding.forum.api.model.vo.user.dto.BaseUserInfoDTO;
import com.github.paicoding.forum.core.permission.UserRole;
import com.github.paicoding.forum.core.util.NumUtil;
import com.github.paicoding.forum.core.util.SpringUtil;
import com.github.paicoding.forum.core.util.id.IdUtil;
import com.github.paicoding.forum.service.article.conveter.ArticleConverter;
import com.github.paicoding.forum.service.article.repository.dao.ArticleDao;
import com.github.paicoding.forum.service.article.repository.dao.ArticleTagDao;
import com.github.paicoding.forum.service.article.repository.entity.ArticleDO;
import com.github.paicoding.forum.service.article.service.ArticleWriteService;
import com.github.paicoding.forum.service.article.service.ColumnSettingService;
import com.github.paicoding.forum.service.image.service.ImageService;
import com.github.paicoding.forum.service.user.service.AuthorWhiteListService;
import com.github.paicoding.forum.service.user.service.UserFootService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;
import javax.annotation.Resource;
import java.util.Date;
import java.util.Objects;
import java.util.Set;

/**
 * 文章操作相关服务类
 * */
@Slf4j
@Service
public class ArticleWriteServiceImpl implements ArticleWriteService {
    

    @Override
    public Long saveArticle(ArticlePostReq req, Long author) { return null; }

    @Override
    public void deleteArticle(Long articleId, Long loginUserId) {}
}
