package com.iz.sandbox.service.impl;

import com.iz.sandbox.config.ObjectTypeMapper;
import com.iz.sandbox.object.dto.ObjectData;
import com.iz.sandbox.object.dto.ObjectDataRequest;
import com.iz.sandbox.entity.ObjectEntity;
import com.iz.sandbox.repository.ObjectRepository;
import com.iz.sandbox.repository.ObjectSpecifications;
import com.iz.sandbox.service.EventPublishingService;
import com.iz.sandbox.service.ObjectNotFoundException;
import com.iz.sandbox.service.ObjectService;
import com.iz.sandbox.service.validation.ValidationService;
import com.iz.sandbox.service.validation.Violation;
import com.iz.sandbox.service.validation.exception.ValidationException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class ObjectServiceImpl implements ObjectService {

    private final ObjectTypeMapper dataMapper;
    private final ObjectRepository repository;

    private final EventPublishingService eventPublishingService;

    private final ValidationService validationService;

    @Override
    @Transactional
    public ObjectData createObject(ObjectDataRequest dto) {
        log.debug("Creating object: {}", dto);

        List<Violation> violations = validationService.validateObject(dto);
        if (!violations.isEmpty()) throw new ValidationException(violations);
        final ObjectEntity entity = repository.saveAndFlush(dataMapper.mapRequestToEntity(dto));
        eventPublishingService.publishObjectCreatedEvent(dataMapper.mapEntityToEventDto(entity));
        return dataMapper.mapEntityToDto(entity);
    }

    @Override
    @Transactional
    public void updateObjectById(UUID objectId, ObjectDataRequest dto) {
        log.debug("Creating object: {}", dto);

        final ObjectEntity entity = repository.findById(objectId).orElseThrow(() -> new ObjectNotFoundException(objectId));
        dataMapper.mapRequestToEntity(dto, entity);
        repository.saveAndFlush(entity);
        eventPublishingService.publishObjectModifiedEvent(dataMapper.mapEntityToEventDto(entity));
    }

    @Override
    public ObjectData getObjectById(UUID objectId) {
        log.debug("Finding objects with id: {}", objectId);
        return dataMapper.mapEntityToDto(
                repository.findById(objectId).orElseThrow(() -> new ObjectNotFoundException(objectId))
        );
    }

    @Override
    @Transactional
    public void deleteObjectById(UUID objectId) {
        log.debug("Deleting object with ID: {}", objectId);

        repository.deleteById(objectId);
        eventPublishingService.publishObjectDeletedEvent(objectId);
    }

    @Override
    public List<ObjectData> getObjects(Instant startDate, Instant endDate) {
        log.debug("Finding objects with startDate: {}  and endDate: {}", startDate, endDate);

        Specification<ObjectEntity> specification = Specification
                .where(startDate == null ? null : ObjectSpecifications.isCreatedAfter(startDate))
                .and(endDate == null ? null : ObjectSpecifications.isCreatedBefore(endDate));

        return repository.findAll(specification).stream()
                .map(dataMapper::mapEntityToDto)
                .collect(Collectors.toList());
    }
}
