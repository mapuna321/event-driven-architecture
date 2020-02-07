package com.learning.event.subscriber.rabbitmq;

import org.springframework.stereotype.Component;

@Component
public class RabbitMessageFanoutQueueSecondListener extends RabbitMessageAbstractListener {

    @Override
    public String getListenerTypeString() {
        return "fanout second";
    }
}