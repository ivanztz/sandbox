package com.iz.sandbox.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@NoArgsConstructor
@Getter
@Setter
public class ObjectInfo {

    private String id;

    private String stringField;

    private Long intField;

    private Double decimalField;

    private String jsonField;

    private Instant createdAt;

    private Instant updatedAt;
}
