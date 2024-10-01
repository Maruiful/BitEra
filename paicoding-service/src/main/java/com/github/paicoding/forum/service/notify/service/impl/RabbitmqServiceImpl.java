package com.github.paicoding.forum.service.notify.service.impl;

import com.github.paicoding.forum.api.model.enums.NotifyTypeEnum;
import com.github.paicoding.forum.core.common.CommonConstants;
import com.github.paicoding.forum.core.rabbitmq.RabbitmqConnection;
import com.github.paicoding.forum.core.rabbitmq.RabbitmqConnectionPool;
import com.github.paicoding.forum.core.util.JsonUtil;
import com.github.paicoding.forum.core.util.SpringUtil;
import com.github.paicoding.forum.service.notify.service.NotifyService;
import com.github.paicoding.forum.service.notify.service.RabbitmqService;
import com.github.paicoding.forum.service.user.repository.entity.UserFootDO;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.util.concurrent.TimeoutException;


@Slf4j
@Service
public class RabbitmqServiceImpl implements RabbitmqService {
    

    @Override
    public boolean enabled() { return false; }

    @Override
    public void publishMsg(String exchange,
                           BuiltinExchangeType exchangeType,
                           String routingKey,
                           String message) {}

    @Override
    public void consumerMsg(String exchange,
                            String queueName,
                            String routingKey) {}

    @Override
    public void processConsumerMsg() {}
}
