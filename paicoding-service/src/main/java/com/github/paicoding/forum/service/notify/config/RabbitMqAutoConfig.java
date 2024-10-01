package com.github.paicoding.forum.service.notify.config;

import com.github.paicoding.forum.core.async.AsyncUtil;
import com.github.paicoding.forum.core.config.RabbitmqProperties;
import com.github.paicoding.forum.core.rabbitmq.RabbitmqConnectionPool;
import com.github.paicoding.forum.service.notify.service.RabbitmqService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/** */
@Configuration
@ConditionalOnProperty(value = "rabbitmq.switchFlag")
@EnableConfigurationProperties(RabbitmqProperties.class)
public class RabbitMqAutoConfig implements ApplicationRunner {
    @Resource
    private RabbitmqService rabbitmqService;

    @Autowired
    private RabbitmqProperties rabbitmqProperties;


    @Override
    public void run(ApplicationArguments args) throws Exception  {}
}
