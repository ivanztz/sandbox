package com.iz.sandbox.listener;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.iz.sandbox.event.ObjectModifiedMessage;
import com.iz.sandbox.model.MessageInfo;
import com.iz.sandbox.service.EventService;
import lombok.AllArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class EventListener {

    private final EventService eventService;

    private final ObjectMapper objectMapper;

    @KafkaListener(topics = "${topics.events}")
    public void processEvent(ConsumerRecord<String, ObjectModifiedMessage> record) throws JsonProcessingException {
        eventService.processEvent(record.value(), new MessageInfo(record.topic(), record.partition(), record.offset()));
    }

}
