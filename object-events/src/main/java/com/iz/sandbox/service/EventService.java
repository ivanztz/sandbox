package com.iz.sandbox.service;

import com.iz.sandbox.dto.ObjectEventData;
import com.iz.sandbox.event.ObjectModifiedMessage;
import com.iz.sandbox.model.MessageInfo;

import java.util.Date;
import java.util.List;

public interface EventService {
    void processEvent(ObjectModifiedMessage data, MessageInfo messageInfo);

    List<ObjectEventData> findEvents(String objectIdm, Date startDate, Date endDate);
}
