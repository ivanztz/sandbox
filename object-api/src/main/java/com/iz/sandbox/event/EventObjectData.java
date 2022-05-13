package com.iz.sandbox.event;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class EventObjectData implements Serializable {
    @JsonProperty("id")
    private UUID id;

    @JsonProperty("stringField")
    private String stringField;

    @JsonProperty("intField")
    private Long intField;

    @JsonProperty("decimalField")
    private Double decimalField;

    @JsonProperty("jsonField")
    private String jsonField;

    @JsonProperty("createdAt")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private OffsetDateTime createdAt;

    @JsonProperty("updatedAt")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private OffsetDateTime updatedAt;
}

