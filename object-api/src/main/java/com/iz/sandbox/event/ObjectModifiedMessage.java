package com.iz.sandbox.event;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
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
public class ObjectModifiedMessage implements Serializable {

    @JsonProperty("objectId")
    private UUID objectId;

    @JsonProperty("publishedAt")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private OffsetDateTime publishedAt;

    @JsonProperty("principal")
    private String principal;

    @JsonProperty("eventType")
    private EventTypeEnum eventType;

    @JsonProperty("data")
    private EventObjectData data;

    public enum EventTypeEnum {
        OBJECT_CREATED("OBJECT_CREATED"),

        OBJECT_UPDATED("OBJECT_UPDATED"),

        OBJECT_DELETED("OBJECT_DELETED");

        private final String value;

        EventTypeEnum(String value) {
            this.value = value;
        }

        @JsonValue
        public String getValue() {
            return value;
        }

        @Override
        public String toString() {
            return String.valueOf(value);
        }

        @JsonCreator
        public static EventTypeEnum fromValue(String value) {
            for (EventTypeEnum b : EventTypeEnum.values()) {
                if (b.value.equals(value)) {
                    return b;
                }
            }
            throw new IllegalArgumentException("Unexpected value '" + value + "'");
        }
    }


}
