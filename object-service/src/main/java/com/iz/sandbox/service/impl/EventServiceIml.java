package com.iz.sandbox.service.impl;

import com.iz.sandbox.dto.ObjectData;
import com.iz.sandbox.event.ObjectDeletedEvent;
import com.iz.sandbox.event.ObjectModifiedEvent;
import com.iz.sandbox.service.EventService;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.util.UUID;


@Service
@Slf4j
public class EventServiceIml implements EventService {

    private final KafkaTemplate<String, Object> kafkaTemplate;
    @Value("${topics.events}")
    private String eventsTopic;

    public EventServiceIml(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public void publishObjectCreatedEvent(ObjectData data) {
        log.debug("Publishing creation event for {}", data);

        final ObjectModifiedEvent event = createEventFromDto(data);
        event.setModificationType(ObjectModifiedEvent.ModificationTypeEnum.CREATED);
        final ProducerRecord<String, Object> record = new ProducerRecord<>(eventsTopic, event);
        kafkaTemplate.send(record);
    }

    private ObjectModifiedEvent createEventFromDto(ObjectData data) {
        final ObjectModifiedEvent event = new ObjectModifiedEvent();
        event.setPublishedAt(OffsetDateTime.now());
        event.setUpdatedAt(data.getUpdatedAt());
        event.setCreatedAt(data.getCreatedAt());
        event.setId(data.getId());
        event.setStringField(data.getStringField());
        event.setIntField(data.getIntField());
        event.setDecimalField(data.getDecimalField());
        event.setJsonField(data.getJsonField());

        return event;
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public void publishObjectModifiedEvent(ObjectData data) {
        log.debug("Publishing updating event for {}", data);

        final ObjectModifiedEvent event = createEventFromDto(data);
        event.setModificationType(ObjectModifiedEvent.ModificationTypeEnum.UPDATED);
        final ProducerRecord<String, Object> record = new ProducerRecord<>(eventsTopic, event);
        kafkaTemplate.send(record);
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public void publishObjectDeletedEvent(UUID id) {
        log.debug("Publishing deleting event for {}", id);

        final ObjectDeletedEvent event = new ObjectDeletedEvent();
        event.setPublishedAt(OffsetDateTime.now());
        event.setId(id);
        final ProducerRecord<String, Object> record = new ProducerRecord<>(eventsTopic, event);
        kafkaTemplate.send(record);
    }
}
