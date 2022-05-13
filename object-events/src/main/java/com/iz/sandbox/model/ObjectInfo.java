package com.iz.sandbox.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.OffsetDateTime;

@NoArgsConstructor
@Getter
@Setter
public class ObjectInfo {

    private String id;

    private String stringField;

    private Long intField;

    private Double decimalField;

    private String jsonField;

    private OffsetDateTime createdAt;

    private OffsetDateTime updatedAt;
}
