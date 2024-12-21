package com.github.paicoding.forum.service.user.service.whitelist;

import com.github.paicoding.forum.api.model.vo.user.dto.BaseUserInfoDTO;
import com.github.paicoding.forum.core.cache.RedisClient;
import com.github.paicoding.forum.service.user.service.AuthorWhiteListService;
import com.github.paicoding.forum.service.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/** */
@Service
public class AuthorWhiteListServiceImpl implements AuthorWhiteListService {
    /**
     * 实用 redis - set 来存储允许直接发文章的白名单
     */
    private static final String ARTICLE_WHITE_LIST = "auth_article_white_list";

    @Override
    public boolean authorInArticleWhiteList(Long authorId) {
        return RedisClient.sIsMember(ARTICLE_WHITE_LIST, authorId);
    }

    @Override
    public List<BaseUserInfoDTO> queryAllArticleWhiteListAuthors() { return null; }

    @Override
    public void addAuthor2ArticleWhitList(Long userId) {}

    @Override
    public void removeAuthorFromArticleWhiteList(Long userId) {}
}
