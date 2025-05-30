package com.iz.sandbox.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@NoArgsConstructor
@Getter
@Setter
@Document(collection = "object-events")
public class ObjectEventDocument {

    @Id
    private String id;

    private String objectId;

    private Instant publishedAt;

    private String principal;

    private String eventType;

    private ObjectInfo data;

    private MessageInfo messageInfo;
}
