package com.learning.event.subsriber.rabbitmq;

import org.springframework.stereotype.Component;

@Component
public class RabbitMessageFanoutQueueListener extends RabbitMessageAbstractListener {

    @Override
    public String getListenerTypeString() {
        return "fanout first";
    }
}