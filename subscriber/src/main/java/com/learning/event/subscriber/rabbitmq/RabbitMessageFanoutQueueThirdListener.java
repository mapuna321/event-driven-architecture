package com.learning.event.subscriber.rabbitmq;

import org.springframework.stereotype.Component;

@Component
public class RabbitMessageFanoutQueueThirdListener extends RabbitMessageAbstractListener {

    @Override
    public String getListenerTypeString() {
        return "fanout third";
    }
}