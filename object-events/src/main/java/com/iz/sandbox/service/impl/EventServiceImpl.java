package com.iz.sandbox.service.impl;

import com.iz.sandbox.config.ObjectEventTypeMapper;
import com.iz.sandbox.dto.ObjectEventData;
import com.iz.sandbox.event.ObjectCreatedMessage;
import com.iz.sandbox.event.ObjectDeletedMessage;
import com.iz.sandbox.event.ObjectUpdatedMessage;
import com.iz.sandbox.model.MessageInfo;
import com.iz.sandbox.model.ObjectEventDocument;
import com.iz.sandbox.repository.EventRepository;
import com.iz.sandbox.service.EventService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@AllArgsConstructor
public class EventServiceImpl implements EventService {

    private final ObjectEventTypeMapper dataMapper;

    private final EventRepository repository;

    @Override
    public void processEvent(Object data, String eventClassName, MessageInfo messageInfo) {
        final ObjectEventDocument eventDocument;
        switch (eventClassName) {
            case "com.iz.sandbox.event.ObjectCreatedMessage":
                eventDocument = dataMapper.mapMessageDataToDocument((ObjectCreatedMessage) data);
                break;
            case "com.iz.sandbox.event.ObjectUpdatedMessage":
                eventDocument = dataMapper.mapMessageDataToDocument((ObjectUpdatedMessage) data);
                break;
            case "com.iz.sandbox.event.ObjectDeletedMessage":
                eventDocument = dataMapper.mapMessageDataToDocument((ObjectDeletedMessage) data);
                break;
            default:
                throw new IllegalArgumentException();
        }

        repository.save(eventDocument);
    }

    @Override
    public List<ObjectEventData> findEvents(String objectId, OffsetDateTime startDate, OffsetDateTime endDate) {
        return repository.findByCriteria(objectId, startDate, endDate).stream()
                .map(dataMapper::mapEventDocumentToDto)
                .collect(Collectors.toList());
    }
}
