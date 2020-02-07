package com.learning.event.subsriber.rabbitmq;

import org.springframework.stereotype.Component;

@Component
public class RabbitMessageFanoutQueueThirdListener extends RabbitMessageAbstractListener {

    @Override
    public String getListenerTypeString() {
        return "fanout third";
    }
}