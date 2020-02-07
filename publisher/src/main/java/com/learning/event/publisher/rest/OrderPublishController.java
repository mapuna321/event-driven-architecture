package com.learning.event.publisher.rest;

import com.learning.event.publisher.service.RabbitSenderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/order/publish")
@Slf4j
@ResponseStatus(HttpStatus.NO_CONTENT)
@RequiredArgsConstructor
public class OrderPublishController {

    private final RabbitSenderService rabbitSenderService;

    @PostMapping("/direct")
    public void publishOrderOnDirectExchange(@RequestBody Map<String, Object> orderPublishRequest) {
        log.info("Order Received for direct exchange {}", orderPublishRequest);
        rabbitSenderService.sendMessageToDirectExchange(orderPublishRequest);
    }

    @PostMapping("/topic")
    public void publishOrderOnTopicExchange(@RequestBody Map<String, Object> orderPublishRequest) {
        log.info("Order Received for topic exchange {}", orderPublishRequest);
        rabbitSenderService.sendMessageToTopicExchange(orderPublishRequest);
    }

    @PostMapping("/fanout")
    public void publishOrderOnFanoutExchange(@RequestBody Map<String, Object> orderPublishRequest) {
        log.info("Order Received for fanout exchange {}", orderPublishRequest);
        rabbitSenderService.sendMessageToFanoutExchange(orderPublishRequest);
    }
}
