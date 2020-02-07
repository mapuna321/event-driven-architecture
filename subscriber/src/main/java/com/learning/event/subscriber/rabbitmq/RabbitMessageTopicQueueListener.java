package com.learning.event.subscriber.rabbitmq;

import org.springframework.stereotype.Component;

@Component
public class RabbitMessageTopicQueueListener extends RabbitMessageAbstractListener {

    @Override
    public String getListenerTypeString() {
        return "topic";
    }
}