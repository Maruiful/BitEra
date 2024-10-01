package com.github.paicoding.forum.service.comment.converter;

import com.github.paicoding.forum.api.model.vo.comment.CommentSaveReq;
import com.github.paicoding.forum.api.model.vo.comment.dto.BaseCommentDTO;
import com.github.paicoding.forum.api.model.vo.comment.dto.SubCommentDTO;
import com.github.paicoding.forum.api.model.vo.comment.dto.TopCommentDTO;
import com.github.paicoding.forum.service.comment.repository.entity.CommentDO;

/**
 * 评论转换
 * */
public class CommentConverter {

    public static CommentDO toDo(CommentSaveReq req) { return null; }

    private static <T extends BaseCommentDTO> void parseDto(CommentDO comment, T sub) {}

    public static TopCommentDTO toTopDto(CommentDO commentDO) { return null; }

    public static SubCommentDTO toSubDto(CommentDO comment) { return null; }
}