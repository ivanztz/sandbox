package com.iz.sandbox.listener;

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

    @KafkaListener(topics = "${topics.events}")
    public void processEvent(ConsumerRecord<String, ObjectModifiedMessage> record) {
        eventService.processEvent(record.value(), new MessageInfo(record.topic(), record.partition(), record.offset()));
    }

}
