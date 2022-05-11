package com.iz.sandbox.event;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@NoArgsConstructor
@Getter
@Setter
public class ObjectDeletedEvent  extends EventBase {

    @JsonProperty("id")
    private UUID id;
}
