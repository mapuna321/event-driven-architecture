package com.learning.event.subsriber.rabbitmq;

import org.springframework.stereotype.Component;

@Component
public class RabbitMessageTopicQueueListener extends RabbitMessageAbstractListener {

    @Override
    public String getListenerTypeString() {
        return "topic";
    }
}