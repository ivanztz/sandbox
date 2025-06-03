package com.iz.sandbox.service.impl;

import com.iz.sandbox.event.EventObjectData;
import com.iz.sandbox.event.EventType;
import com.iz.sandbox.event.ObjectChangedEvent;
import com.iz.sandbox.event.EventData;
import com.iz.sandbox.service.EventPublishingService;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.ZoneOffset;
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

        final ObjectChangedEvent event = createEvent(data.getId(), data, createEventData(EventType.CREATE));
        final ProducerRecord<String, Object> record = new ProducerRecord<>(eventsTopic, event);
        kafkaTemplate.send(record);
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public void publishObjectModifiedEvent(EventObjectData data) {
        log.debug("Publishing updating event for {}", data);

        final ObjectChangedEvent event = createEvent(data.getId(), data, createEventData(EventType.UPDATE));
        final ProducerRecord<String, Object> record = new ProducerRecord<>(eventsTopic, event);
        kafkaTemplate.send(record);
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public void publishObjectDeletedEvent(UUID id) {
        log.debug("Publishing deleting event for {}", id);

        final ObjectChangedEvent event = createEvent(id.toString(), null, createEventData(EventType.UPDATE));
        final ProducerRecord<String, Object> record = new ProducerRecord<>(eventsTopic, event);
        kafkaTemplate.send(record);
    }

    private EventData createEventData(EventType eventType) {
        final EventData eventData = new EventData();
        eventData.setPublishedAt(Instant.now().atOffset(ZoneOffset.UTC));
        eventData.setPrincipal(null);
        eventData.setType(eventType);

        return eventData;
    }

    private ObjectChangedEvent createEvent(String objectId, EventObjectData data, EventData eventData) {
        final ObjectChangedEvent event = new ObjectChangedEvent();
        event.setEventData(eventData);
        event.setEventPayload(data);
        event.setObjectId(objectId);

        return event;
    }

}
