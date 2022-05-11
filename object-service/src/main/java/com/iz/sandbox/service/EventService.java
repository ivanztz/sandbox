package com.iz.sandbox.service;

import com.iz.sandbox.dto.ObjectData;

import java.util.UUID;

public interface EventService {
    void publishObjectCreatedEvent(ObjectData data);
    void publishObjectModifiedEvent(ObjectData data);
    void publishObjectDeletedEvent(UUID id);
}
