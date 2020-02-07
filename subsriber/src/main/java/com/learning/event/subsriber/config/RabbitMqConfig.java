package com.learning.event.subsriber.config;

import com.learning.event.subsriber.rabbitmq.RabbitMessageDirectQueueListener;
import com.learning.event.subsriber.rabbitmq.RabbitMessageFanoutQueueListener;
import com.learning.event.subsriber.rabbitmq.RabbitMessageFanoutQueueSecondListener;
import com.learning.event.subsriber.rabbitmq.RabbitMessageFanoutQueueThirdListener;
import com.learning.event.subsriber.rabbitmq.RabbitMessageTopicQueueListener;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.MessageListener;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.MessageListenerContainer;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
@RequiredArgsConstructor
public class RabbitMqConfig {

    private final SubscriberProperties subscriberProperties;
    private final RabbitMessageDirectQueueListener rabbitMessageDirectQueueListener;
    private final RabbitMessageTopicQueueListener rabbitMessageTopicQueueListener;
    private final RabbitMessageFanoutQueueListener rabbitMessageFanoutQueueListener;
    private final RabbitMessageFanoutQueueSecondListener rabbitMessageFanoutQueueSecondListener;
    private final RabbitMessageFanoutQueueThirdListener rabbitMessageFanoutQueueThirdListener;

    @Bean
    DirectExchange directExchange() {
        return new DirectExchange(subscriberProperties.getRabbitmqConfig().getDirectExchange());
    }

    @Bean
    TopicExchange topicExchange() {
        return new TopicExchange(subscriberProperties.getRabbitmqConfig().getTopicExchange());
    }

    @Bean
    FanoutExchange fanoutExchange() {
        return new FanoutExchange(subscriberProperties.getRabbitmqConfig().getFanoutExchange());
    }

    @Bean
    Queue directExchangeQueue() {
        return new Queue(subscriberProperties.getRabbitmqConfig().getDirectExchangeQueue());
    }

    @Bean
    Queue topicExchangeQueue() {
        return new Queue(subscriberProperties.getRabbitmqConfig().getTopicExchangeQueue());
    }

    @Bean
    Queue fanoutExchangeFirstQueue() {
        return new Queue(subscriberProperties.getRabbitmqConfig().getFanoutExchangeFirstQueue());
    }

    @Bean
    Queue fanoutExchangeSecondQueue() {
        return new Queue(subscriberProperties.getRabbitmqConfig().getFanoutExchangeSecondQueue());
    }

    @Bean
    Queue fanoutExchangeThirdQueue() {
        return new Queue(subscriberProperties.getRabbitmqConfig().getFanoutExchangeThirdQueue());
    }

    @Bean
    List<Binding> bindings(AmqpAdmin amqpAdmin) {
        List<Binding> bindingList = new ArrayList<>();
        Binding binding = BindingBuilder.bind(directExchangeQueue())
                .to(directExchange())
                .with(subscriberProperties.getRabbitmqConfig().getDirectExchangeBindingKey());
        amqpAdmin.declareBinding(binding);
        bindingList.add(binding);

        binding = BindingBuilder.bind(topicExchangeQueue())
                .to(topicExchange())
                .with(subscriberProperties.getRabbitmqConfig().getTopicExchangeBindingKey());
        amqpAdmin.declareBinding(binding);
        bindingList.add(binding);

        binding = BindingBuilder.bind(fanoutExchangeFirstQueue())
                .to(fanoutExchange());
        amqpAdmin.declareBinding(binding);
        bindingList.add(binding);

        binding = BindingBuilder.bind(fanoutExchangeSecondQueue())
                .to(fanoutExchange());
        amqpAdmin.declareBinding(binding);
        bindingList.add(binding);

        binding = BindingBuilder.bind(fanoutExchangeThirdQueue())
                .to(fanoutExchange());
        amqpAdmin.declareBinding(binding);
        bindingList.add(binding);

        return bindingList;
    }

    @Bean
    MessageListenerContainer messageDirectListenerContainer(ConnectionFactory connectionFactory) {
        return getMessageListenerContainer(connectionFactory, rabbitMessageDirectQueueListener, directExchangeQueue());
    }

    @Bean
    MessageListenerContainer messageTopicListenerContainer(ConnectionFactory connectionFactory) {
        return getMessageListenerContainer(connectionFactory, rabbitMessageTopicQueueListener, topicExchangeQueue());
    }

    @Bean
    MessageListenerContainer messageFanoutListenerContainer(ConnectionFactory connectionFactory) {
        return getMessageListenerContainer(connectionFactory, rabbitMessageFanoutQueueListener, fanoutExchangeFirstQueue());
    }

    @Bean
    MessageListenerContainer messageFanoutSecondListenerContainer(ConnectionFactory connectionFactory) {
        return getMessageListenerContainer(connectionFactory, rabbitMessageFanoutQueueSecondListener, fanoutExchangeSecondQueue());
    }

    @Bean
    MessageListenerContainer messageFanoutThirdListenerContainer(ConnectionFactory connectionFactory) {
        return getMessageListenerContainer(connectionFactory, rabbitMessageFanoutQueueThirdListener, fanoutExchangeThirdQueue());
    }

    private MessageListenerContainer getMessageListenerContainer(ConnectionFactory connectionFactory, MessageListener messageListener, Queue queue) {
        SimpleMessageListenerContainer simpleMessageListenerContainer = new SimpleMessageListenerContainer();
        simpleMessageListenerContainer.setConnectionFactory(connectionFactory);
        simpleMessageListenerContainer.setQueues(queue);
        simpleMessageListenerContainer.setMessageListener(messageListener);

        return simpleMessageListenerContainer;
    }
}
