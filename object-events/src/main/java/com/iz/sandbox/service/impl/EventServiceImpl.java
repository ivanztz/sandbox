package com.iz.sandbox.service.impl;

import com.iz.sandbox.config.ObjectEventTypeMapper;
import com.iz.sandbox.dto.ObjectEventData;
import com.iz.sandbox.event.ObjectModifiedMessage;
import com.iz.sandbox.model.MessageInfo;
import com.iz.sandbox.model.ObjectEventDocument;
import com.iz.sandbox.repository.EventRepository;
import com.iz.sandbox.service.EventService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@AllArgsConstructor
public class EventServiceImpl implements EventService {

    private final ObjectEventTypeMapper dataMapper;

    private final EventRepository repository;

    @Override
    public void processEvent(ObjectModifiedMessage message, MessageInfo messageInfo) {
        log.debug("Saving event data: {} from message {}", message, messageInfo);

        final ObjectEventDocument eventDocument = dataMapper.mapMessageDataToDocument(message);
        eventDocument.setMessageInfo(messageInfo);
        repository.save(eventDocument);
    }

    @Override
    public List<ObjectEventData> findEvents(String objectIdm, Date startDate, Date endDate) {
        return repository.findAll().stream()
                .map(dataMapper::mapEventDocumentToDto)
                .collect(Collectors.toList());
    }
}
