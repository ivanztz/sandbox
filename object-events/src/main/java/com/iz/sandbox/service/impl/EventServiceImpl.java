package com.iz.sandbox.service.impl;

import com.iz.sandbox.config.ObjectEventTypeMapper;
import com.iz.sandbox.object.event.dto.ObjectEventData;
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

import java.time.Instant;
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
        log.info("Processing {}", data);
        final ObjectEventDocument eventDocument = switch (eventClassName) {
            case "com.iz.sandbox.event.ObjectCreatedMessage" ->
                    dataMapper.mapMessageDataToDocument((ObjectCreatedMessage) data);
            case "com.iz.sandbox.event.ObjectUpdatedMessage" ->
                    dataMapper.mapMessageDataToDocument((ObjectUpdatedMessage) data);
            case "com.iz.sandbox.event.ObjectDeletedMessage" ->
                    dataMapper.mapMessageDataToDocument((ObjectDeletedMessage) data);
            default -> throw new IllegalArgumentException();
        };

        repository.save(eventDocument);
        log.info("Successfully processed {}", data);
    }

    @Override
    public List<ObjectEventData> findEvents(String objectId, Instant startDate, Instant endDate) {
        return repository.findByCriteria(objectId, startDate, endDate).stream()
                .map(dataMapper::mapEventDocumentToDto)
                .collect(Collectors.toList());
    }
}
