package com.iz.sandbox.controller;

import com.iz.sandbox.object.event.api.EventsApi;
import com.iz.sandbox.object.event.dto.ObjectEventDataListResponse;
import com.iz.sandbox.service.EventService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.UUID;

@RestController("/events")
@AllArgsConstructor
public class EventController implements EventsApi {

    private EventService eventService;

    @Override
    public ResponseEntity<ObjectEventDataListResponse> getEvents(UUID objectId, Instant startDate, Instant endDate) {
        final ObjectEventDataListResponse response = new ObjectEventDataListResponse();

        response.setData(eventService.findEvents(objectId != null ? objectId.toString() : null, startDate, endDate));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
