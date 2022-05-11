package com.iz.sandbox.event;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.OffsetDateTime;
import java.util.UUID;

@NoArgsConstructor
@Getter
@Setter
public class ObjectModifiedEvent extends EventBase {

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

    @JsonProperty("modificationType")
    private ModificationTypeEnum modificationType;

    public enum ModificationTypeEnum {
        CREATED("CREATED"),

        UPDATED("UPDATED");

        private final String value;

        ModificationTypeEnum(String value) {
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
        public static ModificationTypeEnum fromValue(String value) {
            for (ModificationTypeEnum b : ModificationTypeEnum.values()) {
                if (b.value.equals(value)) {
                    return b;
                }
            }
            throw new IllegalArgumentException("Unexpected value '" + value + "'");
        }
    }
}
