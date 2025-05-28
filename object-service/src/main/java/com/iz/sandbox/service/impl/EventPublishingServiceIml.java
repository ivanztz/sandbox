package com.iz.sandbox.service.impl;

import com.iz.sandbox.event.*;
import com.iz.sandbox.service.EventPublishingService;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.util.UUID;

@Service
@Slf4j
public class EventPublishingServiceIml implements EventPublishingService {

    private final KafkaTemplate<String, Object> kafkaTemplate;
    @Value("${topics.events}")
    private String eventsTopic;

    public EventPublishingServiceIml(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public void publishObjectCreatedEvent(EventObjectData data) {
        log.debug("Publishing creation event for {}", data);

        final ObjectCreatedMessage event = ObjectCreatedMessage.newBuilder()
                .setObjectId(data.getId())
                .setEventPayload(data)
                .setEventData(createEventData())
                .build();
        final ProducerRecord<String, Object> record = new ProducerRecord<>(eventsTopic, event);
        kafkaTemplate.send(record);
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public void publishObjectModifiedEvent(EventObjectData data) {
        log.debug("Publishing updating event for {}", data);

        final ObjectUpdatedMessage event = ObjectUpdatedMessage.newBuilder()
                .setObjectId(data.getId())
                .setEventPayload(data)
                .setEventData(createEventData())
                .build();
        final ProducerRecord<String, Object> record = new ProducerRecord<>(eventsTopic, event);
        kafkaTemplate.send(record);
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public void publishObjectDeletedEvent(UUID id) {
        log.debug("Publishing deleting event for {}", id);

        final ObjectDeletedMessage event = ObjectDeletedMessage.newBuilder()
                .setObjectId(id)
                .setEventData(createEventData())
                .build();
        final ProducerRecord<String, Object> record = new ProducerRecord<>(eventsTopic, event);
        kafkaTemplate.send(record);
    }

    private EventData createEventData() {
        return EventData.newBuilder()
                .setPrincipal(null)
                .setPublishedAt(OffsetDateTime.now())
                .build();
    }

}
