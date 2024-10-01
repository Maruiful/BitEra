package com.github.paicoding.forum.service.shortlink.service.impl;

import com.github.paicoding.forum.api.model.context.ReqInfoContext;
import com.github.paicoding.forum.service.shortlink.repository.entity.ShortLinkDO;
import com.github.paicoding.forum.api.model.vo.shortlink.dto.ShortLinkDTO;
import com.github.paicoding.forum.service.shortlink.repository.entity.ShortLinkRecordDO;
import com.github.paicoding.forum.api.model.vo.shortlink.ShortLinkVO;
import com.github.paicoding.forum.core.cache.RedisClient;
import com.github.paicoding.forum.service.shortlink.help.ShortCodeGenerator;
import com.github.paicoding.forum.service.shortlink.help.SourceDetector;
import com.github.paicoding.forum.service.shortlink.repository.mapper.ShortLinkMapper;
import com.github.paicoding.forum.service.shortlink.repository.mapper.ShortLinkRecordMapper;
import com.github.paicoding.forum.service.shortlink.service.ShortLinkService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import javax.annotation.Resource;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;

/**
     * 创建短链接
     *
     * @param shortLinkDTO 包含原始URL和用户信息的数据传输对象
     * @return 包含短链接和原始URL的ShortLinkVO对象
     * @throws NoSuchAlgorithmException 如果生成短码时发生错误
     */
@Slf4j
@Service
public class ShortLinkServiceImpl implements ShortLinkService {
    

    @Override
    public ShortLinkVO createShortLink(ShortLinkDTO shortLinkDTO) throws NoSuchAlgorithmException   { return null; }

    @Override
    public ShortLinkVO getOriginalLink(String shortCode) { return null; }
}
