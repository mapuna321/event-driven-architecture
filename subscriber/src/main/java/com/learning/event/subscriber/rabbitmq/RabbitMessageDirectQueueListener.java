package com.learning.event.subscriber.rabbitmq;

import org.springframework.stereotype.Component;

@Component
public class RabbitMessageDirectQueueListener extends RabbitMessageAbstractListener {

    @Override
    public String getListenerTypeString() {
        return "direct";
    }
}