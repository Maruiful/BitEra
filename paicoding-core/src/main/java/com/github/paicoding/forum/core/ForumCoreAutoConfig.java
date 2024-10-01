package com.github.paicoding.forum.core;

import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.paicoding.forum.core.cache.RedisClient;
import com.github.paicoding.forum.core.config.ProxyProperties;
import com.github.paicoding.forum.core.net.ProxyCenter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.PostConstruct;
import java.util.concurrent.TimeUnit;

/** */
@Configuration
@EnableConfigurationProperties(ProxyProperties.class)
@ComponentScan(basePackages = "com.github.paicoding.forum.core")
public class ForumCoreAutoConfig {
    @Autowired
    private ProxyProperties proxyProperties;

    public ForumCoreAutoConfig(RedisTemplate<String, String> redisTemplate) {
        RedisClient.register(redisTemplate);
    }

    /**
     * 定义缓存管理器，配合Spring的 @Cache 来使用
     *
     * @return
     */
    @Bean("caffeineCacheManager")
    public CacheManager cacheManager()  { return null; }

    @PostConstruct
    public void init()  {}
}
