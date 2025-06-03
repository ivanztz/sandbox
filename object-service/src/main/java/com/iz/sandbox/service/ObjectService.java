package com.iz.sandbox.service;

import com.iz.sandbox.object.dto.ObjectData;
import com.iz.sandbox.object.dto.ObjectDataRequest;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

public interface ObjectService {

    ObjectData createObject(ObjectDataRequest dto);

    void updateObjectById(UUID objectId, ObjectDataRequest dto);

    ObjectData getObjectById(UUID objectId);

    void deleteObjectById(UUID objectId);

    List<ObjectData> getObjects(OffsetDateTime startDate, OffsetDateTime endDate);
}
