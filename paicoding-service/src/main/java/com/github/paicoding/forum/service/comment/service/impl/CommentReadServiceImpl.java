package com.github.paicoding.forum.service.comment.service.impl;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.github.paicoding.forum.api.model.context.ReqInfoContext;
import com.github.paicoding.forum.api.model.enums.DocumentTypeEnum;
import com.github.paicoding.forum.api.model.enums.PraiseStatEnum;
import com.github.paicoding.forum.api.model.vo.PageParam;
import com.github.paicoding.forum.api.model.vo.comment.dto.BaseCommentDTO;
import com.github.paicoding.forum.api.model.vo.comment.dto.SubCommentDTO;
import com.github.paicoding.forum.api.model.vo.comment.dto.TopCommentDTO;
import com.github.paicoding.forum.api.model.vo.user.dto.BaseUserInfoDTO;
import com.github.paicoding.forum.service.comment.converter.CommentConverter;
import com.github.paicoding.forum.service.comment.repository.dao.CommentDao;
import com.github.paicoding.forum.service.comment.repository.entity.CommentDO;
import com.github.paicoding.forum.service.comment.service.CommentReadService;
import com.github.paicoding.forum.service.user.repository.entity.UserFootDO;
import com.github.paicoding.forum.service.statistics.service.CountService;
import com.github.paicoding.forum.service.user.service.UserFootService;
import com.github.paicoding.forum.service.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 评论Service
 * */
@Service
public class CommentReadServiceImpl implements CommentReadService {
    

    @Override
    public CommentDO queryComment(Long commentId) { return null; }

    @Override
    public List<TopCommentDTO> getArticleComments(Long articleId, PageParam page) { return null; }

    @Override
    public TopCommentDTO queryHotComment(Long articleId) { return null; }

    @Override
    public int queryCommentCount(Long articleId) { return 0; }
}
