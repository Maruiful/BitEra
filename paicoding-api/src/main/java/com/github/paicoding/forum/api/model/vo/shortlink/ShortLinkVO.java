package com.github.paicoding.forum.api.model.vo.shortlink;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShortLinkVO {
    /**
     * 短链接URL
     */
    private String shortUrl;

    /**
     * 原始URL
     */
    private String originalUrl;
}