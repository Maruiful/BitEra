package com.github.paicoding.forum.service.user.service.whitelist;

import com.github.paicoding.forum.api.model.enums.user.UserAIStatEnum;
import com.github.paicoding.forum.api.model.vo.PageVo;
import com.github.paicoding.forum.api.model.vo.user.SearchZsxqUserReq;
import com.github.paicoding.forum.api.model.vo.user.ZsxqUserPostReq;
import com.github.paicoding.forum.api.model.vo.user.dto.ZsxqUserInfoDTO;
import com.github.paicoding.forum.service.user.service.ZsxqWhiteListService;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * 微信搜索「沉默王二」，回复 Java
 * */
@Service
public class ZsxqWhiteListServiceImpl implements ZsxqWhiteListService {

    @Override
    public PageVo<ZsxqUserInfoDTO> getList(SearchZsxqUserReq req) { return null; }

    @Override
    public void operate(Long id, UserAIStatEnum operate) {}

    @Override
    public void update(ZsxqUserPostReq req) {}

    @Override
    public void batchOperate(List<Long> ids, UserAIStatEnum operate) {}

    @Override
    public void reset(Integer authorId) {}
}