package com.iz.sandbox.controller;

import com.iz.sandbox.api.EventsApi;
import com.iz.sandbox.dto.ObjectEventDataListResponse;
import com.iz.sandbox.service.EventService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.UUID;

@RestController("/events")
@AllArgsConstructor
public class EventController implements EventsApi {

    private EventService eventService;

    @Override
    public ResponseEntity<ObjectEventDataListResponse> getEvents(UUID objectId, OffsetDateTime startDate, OffsetDateTime endDate) {
        final ObjectEventDataListResponse response = new ObjectEventDataListResponse();

        response.setData(eventService.findEvents(objectId != null ? objectId.toString() : null,
                startDate != null ? Date.from(startDate.toInstant().atZone(ZoneOffset.UTC).toInstant()) : null,
                endDate != null ? Date.from(endDate.toInstant().atZone(ZoneOffset.UTC).toInstant()) : null));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
