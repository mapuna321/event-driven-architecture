package com.learning.event.publisher.config;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.BiFunction;

@Configuration
@RequiredArgsConstructor
public class RabbitConfig {

    private final PublisherProperties publisherProperties;

    @Bean
    DirectExchange directExchange() {
        return new DirectExchange(publisherProperties.getRabbitmqConfig().getDirectExchange());
    }

    @Bean
    TopicExchange topicExchange() {
        return new TopicExchange(publisherProperties.getRabbitmqConfig().getTopicExchange());
    }

    @Bean
    FanoutExchange fanoutExchange() {
        return new FanoutExchange(publisherProperties.getRabbitmqConfig().getFanoutExchange());
    }

    @Bean
    MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate rabbitDirectExchangeTemplate(ConnectionFactory connectionFactory) {
        return getRabbitTemplateFunction.apply(connectionFactory, directExchange().getName());
    }

    @Bean
    public AmqpTemplate rabbitTopicExchangeTemplate(ConnectionFactory connectionFactory) {
        return getRabbitTemplateFunction.apply(connectionFactory, topicExchange().getName());
    }

    @Bean
    public AmqpTemplate rabbitFanoutExchangeTemplate(ConnectionFactory connectionFactory) {
        return getRabbitTemplateFunction.apply(connectionFactory, fanoutExchange().getName());
    }

    BiFunction<ConnectionFactory, String, RabbitTemplate> getRabbitTemplateFunction = ((connectionFactory, exchangeName) -> {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jsonMessageConverter());
        rabbitTemplate.setExchange(exchangeName);
        return rabbitTemplate;
    });
}
