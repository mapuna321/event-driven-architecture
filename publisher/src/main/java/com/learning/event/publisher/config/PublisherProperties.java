package com.learning.event.publisher.config;

import lombok.Data;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "publisher")
@Getter
public class PublisherProperties {

    private final RabbitmqConfig rabbitmqConfig = new RabbitmqConfig();

    @Data
    public static class RabbitmqConfig {
        private String directExchange;
        private String topicExchange;
        private String fanoutExchange;
        private String directExchangeRoutingKey;
        private String topicExchangeRoutingKey;
    }
}
