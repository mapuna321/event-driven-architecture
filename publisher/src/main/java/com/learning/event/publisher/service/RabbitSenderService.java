package com.learning.event.publisher.service;

import java.util.Map;

public interface RabbitSenderService {

    void sendMessageToDirectExchange(Map<String, Object> rabbitMessage);

    void sendMessageToTopicExchange(Map<String, Object> rabbitMessage);

    void sendMessageToFanoutExchange(Map<String, Object> rabbitMessage);
}
