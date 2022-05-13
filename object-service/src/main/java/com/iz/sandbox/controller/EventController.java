package com.iz.sandbox.controller;

import com.iz.sandbox.api.EventsApi;
import com.iz.sandbox.dto.ObjectEventDataListResponse;
import com.iz.sandbox.feign.EventsApiClient;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.time.OffsetDateTime;
import java.util.UUID;

@RestController
@AllArgsConstructor
public class EventController implements EventsApi {

    private final EventsApiClient client;

    @Override
    public ResponseEntity<ObjectEventDataListResponse> getEvents(UUID objectId, OffsetDateTime startDate, OffsetDateTime endDate) {
        return client.getEvents(objectId, startDate, endDate);
    }
}
