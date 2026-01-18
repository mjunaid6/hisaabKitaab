package com.hisaabkitaab.backend.eventProducer;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import com.hisaabkitaab.backend.dto.UserDto;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserProducer {

    private final KafkaTemplate<String, UserDto> kafkaTemplate;

    @Value("${app.kafka.topic-name}")
    private String TOPIC_NAME;

    public void sendMessageToKafkaTopic(UserDto userDto) {

        Message<UserDto> message = MessageBuilder
                                                .withPayload(userDto)
                                                .setHeader(KafkaHeaders.TOPIC, TOPIC_NAME)
                                                .build();

        kafkaTemplate.send(message);
    }
}
