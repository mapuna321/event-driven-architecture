package com.learning.event.subsriber.rabbitmq;

import org.springframework.stereotype.Component;

@Component
public class RabbitMessageFanoutQueueSecondListener extends RabbitMessageAbstractListener {

    @Override
    public String getListenerTypeString() {
        return "fanout second";
    }
}