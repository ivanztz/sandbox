package com.iz.sandbox.listener;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.iz.sandbox.model.MessageInfo;
import com.iz.sandbox.service.EventService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Slf4j
public class EventListener {

    private final EventService eventService;

    private final ObjectMapper objectMapper;

    @KafkaListener(topics = "${topics.events}")
    public void processEvent(ConsumerRecord<String, Object> record) {
        try {
            eventService.processEvent(record.value(), record.value().getClass().getName(), new MessageInfo(record.topic(), record.partition(), record.offset()));

        } catch (Exception e) {
            log.error("Error processing message", e);
        }
    }
}
