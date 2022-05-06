package com.iz.sandbox.service.impl;

import com.iz.sandbox.config.ObjectMapper;
import com.iz.sandbox.dto.ObjectData;
import com.iz.sandbox.dto.ObjectDataRequest;
import com.iz.sandbox.entity.ObjectEntity;
import com.iz.sandbox.repository.ObjectRepository;
import com.iz.sandbox.repository.ObjectSpecifications;
import com.iz.sandbox.service.ObjectNotFoundException;
import com.iz.sandbox.service.ObjectService;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ObjectServiceImpl implements ObjectService {

    private final ObjectMapper dataMapper;
    private final ObjectRepository repository;

    @Override
    @Transactional
    public ObjectData createObject(ObjectDataRequest dto) {
        return dataMapper.mapEntityToDto(
                repository.saveAndFlush(
                        dataMapper.mapRequestToEntity(dto)
                )
        );
    }

    @Override
    @Transactional
    public void updateObjectById(UUID objectId, ObjectDataRequest dto) {
        final ObjectEntity entity = repository.findById(objectId).orElseThrow(() -> new ObjectNotFoundException(objectId));
        dataMapper.mapRequestToEntity(dto, entity);
        repository.saveAndFlush(entity);
    }

    @Override
    public ObjectData getObjectById(UUID objectId) {
        return dataMapper.mapEntityToDto(
                repository.findById(objectId).orElseThrow(() -> new ObjectNotFoundException(objectId))
        );
    }

    @Override
    @Transactional
    public void deleteObjectById(UUID objectId) {
        repository.deleteById(objectId);
    }

    @Override
    public List<ObjectData> getObjects(OffsetDateTime startDate, OffsetDateTime endDate) {
        Specification<ObjectEntity> specification = Specification
                .where(startDate == null ? null : ObjectSpecifications.isCreatedAfter(startDate))
                .and(endDate == null ? null : ObjectSpecifications.isCreatedBefore(endDate));

        return repository.findAll(specification).stream()
                .map(dataMapper::mapEntityToDto)
                .collect(Collectors.toList());
    }
}
