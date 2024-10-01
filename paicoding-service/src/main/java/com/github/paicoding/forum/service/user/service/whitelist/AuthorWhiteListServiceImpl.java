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
    @Autowired
    

    @Override
    public boolean authorInArticleWhiteList(Long authorId) { return false; }

    @Override
    public List<BaseUserInfoDTO> queryAllArticleWhiteListAuthors() { return null; }

    @Override
    public void addAuthor2ArticleWhitList(Long userId) {}

    @Override
    public void removeAuthorFromArticleWhiteList(Long userId) {}
}
