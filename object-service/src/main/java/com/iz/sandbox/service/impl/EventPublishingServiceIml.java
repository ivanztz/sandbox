package com.iz.sandbox.service.impl;

import com.iz.sandbox.event.EventObjectData;
import com.iz.sandbox.event.EventType;
import com.iz.sandbox.event.ObjectModifiedMessage;
import com.iz.sandbox.service.EventPublishingService;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.core.context.SecurityContextHolder;
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

        final ObjectModifiedMessage event = createEventFromDto(data);
        event.setObjectId(data.getId());
        event.setEventType(EventType.CREATED);
        final ProducerRecord<String, Object> record = new ProducerRecord<>(eventsTopic, event);
        kafkaTemplate.send(record);
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public void publishObjectModifiedEvent(EventObjectData data) {
        log.debug("Publishing updating event for {}", data);

        final ObjectModifiedMessage event = createEventFromDto(data);
        event.setObjectId(data.getId());
        event.setEventType(EventType.UPDATED);
        final ProducerRecord<String, Object> record = new ProducerRecord<>(eventsTopic, event);
        kafkaTemplate.send(record);
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public void publishObjectDeletedEvent(UUID id) {
        log.debug("Publishing deleting event for {}", id);

        final ObjectModifiedMessage event = new ObjectModifiedMessage();
        event.setObjectId(id);
        event.setEventType(EventType.DELETED);
        event.setPublishedAt(OffsetDateTime.now());
        final ProducerRecord<String, Object> record = new ProducerRecord<>(eventsTopic, event);
        kafkaTemplate.send(record);
    }

    private ObjectModifiedMessage createEventFromDto(EventObjectData data) {
        final ObjectModifiedMessage event = new ObjectModifiedMessage();
        event.setData(new EventObjectData());
        event.setPublishedAt(OffsetDateTime.now());
        event.getData().setUpdatedAt(data.getUpdatedAt());
        event.getData().setCreatedAt(data.getCreatedAt());
        event.getData().setId(data.getId());
        event.getData().setStringField(data.getStringField());
        event.getData().setIntField(data.getIntField());
        event.getData().setDecimalField(data.getDecimalField());
        event.getData().setJsonField(data.getJsonField());
        event.setPrincipal(SecurityContextHolder.getContext().getAuthentication().getName());

        return event;
    }

}
