package com.iz.sandbox.service;

import com.iz.sandbox.event.EventObjectData;

import java.util.UUID;

public interface EventPublishingService {
    void publishObjectCreatedEvent(EventObjectData data);

    void publishObjectModifiedEvent(EventObjectData data);

    void publishObjectDeletedEvent(UUID id);
}
