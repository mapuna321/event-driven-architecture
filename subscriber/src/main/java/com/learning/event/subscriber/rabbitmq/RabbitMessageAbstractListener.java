package com.learning.event.subscriber.rabbitmq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.amqp.core.MessageProperties;

@Slf4j
public abstract class RabbitMessageAbstractListener implements MessageListener {

    @Override
    public void onMessage(Message message) {
        MessageProperties messageProperties = message.getMessageProperties();
        log.info("Receiving {} message from exchange {} on queue {} with routing key {} message {}",
                getListenerTypeString(),
                messageProperties.getReceivedExchange(),
                messageProperties.getConsumerQueue(),
                messageProperties.getReceivedRoutingKey(),
                new String(message.getBody())
        );
    }

    public abstract String getListenerTypeString();
}
