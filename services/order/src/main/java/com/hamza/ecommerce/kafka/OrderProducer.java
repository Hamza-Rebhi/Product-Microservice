package com.hamza.ecommerce.kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import static org.springframework.kafka.support.KafkaHeaders.TOPIC;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderProducer {
    private  final KafkaTemplate<String,OrderConfirmation> kafkaTemplate;
    public void sendOrderConfirmation(OrderConfirmation orderConfirmation) {
        log.info("Sending order confirmation");
        Message<OrderConfirmation> message= MessageBuilder.withPayload(orderConfirmation)
                .setHeader(TOPIC,"Order-topic")
                .build();
        kafkaTemplate.send(message);

    }

    }
