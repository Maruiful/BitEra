package com.github.paicoding.forum.core.senstive;

import com.github.houbb.sensitive.word.api.IWordAllow;
import com.github.houbb.sensitive.word.api.IWordDeny;
import com.github.houbb.sensitive.word.bs.SensitiveWordBs;
import com.github.houbb.sensitive.word.support.allow.WordAllowSystem;
import com.github.houbb.sensitive.word.support.deny.WordDenySystem;
import com.github.paicoding.forum.core.autoconf.DynamicConfigContainer;
import com.github.paicoding.forum.core.cache.RedisClient;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.BooleanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * 敏感词服务类
 * */
@Slf4j
@Service
public class SensitiveService {
    /**
     * 敏感词命中计数统计
     */
    private static final String SENSITIVE_WORD_CNT_PREFIX = "sensitive_word";
    private volatile SensitiveWordBs sensitiveWordBs;
    @Autowired
    private SensitiveProperty sensitiveConfig;
    @Autowired
    private DynamicConfigContainer dynamicConfigContainer;

    @PostConstruct
    public void refresh()  {}

    /**
     * 判断是否包含敏感词
     *
     * @param txt 需要校验的文本
     * @return 返回命中的敏感词
     */
    public List<String> contains(String txt)  { return null; }


    /**
     * 返回已命中的敏感词
     *
     * @return key: 敏感词， value：计数
     */
    public Map<String, Integer> getHitSensitiveWords()  { return null; }

    /**
     * 移除敏感词
     *
     * @param word
     */
    public void removeSensitiveWord(String word)  {}

    /**
     * 敏感词替换
     *
     * @param txt
     * @return
     */
    public String replace(String txt)  { return null; }

    /**
     * 查询文本中所有命中的敏感词
     *
     * @param txt 校验文本
     * @return 命中的敏感词
     */
    public List<String> findAll(String txt)  { return null; }
}
