package com.learning.event.subsriber.rabbitmq;

import org.springframework.stereotype.Component;

@Component
public class RabbitMessageDirectQueueListener extends RabbitMessageAbstractListener {

    @Override
    public String getListenerTypeString() {
        return "direct";
    }
}