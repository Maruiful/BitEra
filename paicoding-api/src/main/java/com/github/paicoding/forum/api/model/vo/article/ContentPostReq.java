package com.github.paicoding.forum.api.model.vo.article;

import lombok.Data;

import java.io.Serializable;

@Data
public class ContentPostReq implements Serializable {
    /**
     * 正文内容
     */
    private String content;
}