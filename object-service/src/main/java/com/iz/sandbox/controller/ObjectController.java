package com.iz.sandbox.controller;

import com.iz.sandbox.api.ObjectsApi;
import com.iz.sandbox.dto.ObjectData;
import com.iz.sandbox.dto.ObjectDataListResponse;
import com.iz.sandbox.dto.ObjectDataRequest;
import com.iz.sandbox.dto.ObjectDataResponse;
import com.iz.sandbox.service.ObjectService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.time.OffsetDateTime;
import java.util.UUID;

@Slf4j
@RestController
@AllArgsConstructor
public class ObjectController implements ObjectsApi {

    private final ObjectService objectService;

    @Override
    public ResponseEntity<ObjectDataResponse> createObject(ObjectDataRequest objectDataRequest) {
        final ObjectDataResponse response = new ObjectDataResponse();
        final ObjectData data = objectService.createObject(objectDataRequest);
        response.setData(data);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Void> deleteObjectById(UUID objectId) {
        objectService.deleteObjectById(objectId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ObjectDataResponse> getObjectById(UUID objectId) {
        final ObjectData objectData = objectService.getObjectById(objectId);
        final ObjectDataResponse response = new ObjectDataResponse();
        response.setData(objectData);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ObjectDataListResponse> getObjects(OffsetDateTime startDate, OffsetDateTime endDate) {
        final ObjectDataListResponse response = new ObjectDataListResponse();
        response.setData(objectService.getObjects(startDate, endDate));
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<Void> updateObjectById(UUID objectId, ObjectDataRequest objectDataRequest) {
        objectService.updateObjectById(objectId, objectDataRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
