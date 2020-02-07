package com.learning.event.publisher.service.impl;

import com.learning.event.publisher.config.PublisherProperties;
import com.learning.event.publisher.service.RabbitSenderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class RabbitSenderServiceImpl implements RabbitSenderService {

    private final AmqpTemplate rabbitDirectExchangeTemplate;
    private final AmqpTemplate rabbitTopicExchangeTemplate;
    private final AmqpTemplate rabbitFanoutExchangeTemplate;
    private final PublisherProperties publisherProperties;

    @Override
    public void sendMessageToDirectExchange(Map<String, Object> rabbitMessage) {
        sendMessageToExchange(publisherProperties.getRabbitmqConfig().getDirectExchangeRoutingKey(), publisherProperties.getRabbitmqConfig().getDirectExchange(), rabbitMessage, "direct", rabbitDirectExchangeTemplate);
    }

    @Override
    public void sendMessageToTopicExchange(Map<String, Object> rabbitMessage) {
        sendMessageToExchange(publisherProperties.getRabbitmqConfig().getTopicExchangeRoutingKey(), publisherProperties.getRabbitmqConfig().getTopicExchange(), rabbitMessage, "topic", rabbitTopicExchangeTemplate);
    }

    @Override
    public void sendMessageToFanoutExchange(Map<String, Object> rabbitMessage) {
        sendMessageToExchange("", publisherProperties.getRabbitmqConfig().getFanoutExchange(), rabbitMessage, "fanout", rabbitFanoutExchangeTemplate);
    }

    private void sendMessageToExchange(String routingKey, String exchangeName, Map<String, Object> rabbitMessage, String exchangeType, AmqpTemplate amqpTemplate) {
        log.info("Publishing message {} to {} exchange {} with key {}", rabbitMessage, exchangeType, exchangeName, routingKey);
        amqpTemplate.convertAndSend(routingKey, rabbitMessage);
    }
}
