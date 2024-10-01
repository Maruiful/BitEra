package com.github.paicoding.forum.service.user.service.userfoot;

import com.github.paicoding.forum.api.model.enums.DocumentTypeEnum;
import com.github.paicoding.forum.api.model.enums.OperateTypeEnum;
import com.github.paicoding.forum.api.model.vo.PageParam;
import com.github.paicoding.forum.api.model.vo.user.dto.SimpleUserInfoDTO;
import com.github.paicoding.forum.api.model.vo.user.dto.UserFootStatisticDTO;
import com.github.paicoding.forum.service.comment.repository.entity.CommentDO;
import com.github.paicoding.forum.service.user.repository.entity.UserFootDO;
import com.github.paicoding.forum.service.user.service.UserFootService;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * 用户足迹Service
 * */
@Service
public class UserFootServiceImpl implements UserFootService {
    
    @Override
    public UserFootDO saveOrUpdateUserFoot(DocumentTypeEnum documentType, Long documentId, Long authorId, Long userId, OperateTypeEnum operateTypeEnum) { return null; }

    @Override
    public void favorArticleComment(DocumentTypeEnum documentType, Long documentId, Long authorId, Long userId, OperateTypeEnum operateTypeEnum) {}

    @Override
    public void saveCommentFoot(CommentDO comment, Long articleAuthor, Long parentCommentAuthor) {}

    @Override
    public void removeCommentFoot(CommentDO comment, Long articleAuthor, Long parentCommentAuthor) {}

    @Override
    public List<Long> queryUserReadArticleList(Long userId, PageParam pageParam) { return null; }

    @Override
    public List<Long> queryUserCollectionArticleList(Long userId, PageParam pageParam) { return null; }

    @Override
    public List<SimpleUserInfoDTO> queryArticlePraisedUsers(Long articleId) { return null; }

    @Override
    public UserFootDO queryUserFoot(Long documentId, Integer type, Long userId) { return null; }

    @Override
    public UserFootStatisticDTO getFootCount() { return null; }
}