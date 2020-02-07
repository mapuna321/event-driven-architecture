package com.learning.event.subscriber.rabbitmq;

import org.springframework.stereotype.Component;

@Component
public class RabbitMessageFanoutQueueListener extends RabbitMessageAbstractListener {

    @Override
    public String getListenerTypeString() {
        return "fanout first";
    }
}