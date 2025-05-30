package com.iz.sandbox.controller;

import com.iz.sandbox.config.ObjectTypeMapper;
import com.iz.sandbox.feign.EventsApiClient;
import com.iz.sandbox.object.api.EventsApi;
import com.iz.sandbox.object.dto.ObjectEventDataListResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.UUID;

@RestController
@AllArgsConstructor
public class EventController implements EventsApi {

    private final EventsApiClient client;

    private final ObjectTypeMapper typeMapper;
    @Override
    public ResponseEntity<ObjectEventDataListResponse> getEvents(UUID objectId, Instant startDate, Instant endDate) {
        return ResponseEntity.ok(typeMapper.mapEventDataList(client.getEvents(objectId, startDate, endDate).getBody()));
    }
}
