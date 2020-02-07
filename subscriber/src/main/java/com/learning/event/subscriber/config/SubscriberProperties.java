package com.learning.event.subscriber.config;

import lombok.Data;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "subscriber")
@Getter
public class SubscriberProperties {

    private final RabbitmqConfig rabbitmqConfig = new RabbitmqConfig();

    @Data
    public static class RabbitmqConfig {
        private String directExchange;
        private String topicExchange;
        private String fanoutExchange;
        private String directExchangeBindingKey;
        private String topicExchangeBindingKey;
        private String directExchangeQueue;
        private String topicExchangeQueue;
        private String fanoutExchangeFirstQueue;
        private String fanoutExchangeSecondQueue;
        private String fanoutExchangeThirdQueue;
    }
}
