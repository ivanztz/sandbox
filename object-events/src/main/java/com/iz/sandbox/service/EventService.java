package com.iz.sandbox.service;

import com.iz.sandbox.object.event.dto.ObjectEventData;
import com.iz.sandbox.model.MessageInfo;

import java.time.Instant;
import java.util.List;

public interface EventService {
    void processEvent(Object data, String eventClass, MessageInfo messageInfo);

    List<ObjectEventData> findEvents(String objectId, Instant startDate, Instant endDate);
}
