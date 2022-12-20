package com.iz.sandbox.config;

import com.iz.sandbox.dto.ObjectData;
import com.iz.sandbox.dto.ObjectEventData;
import com.iz.sandbox.event.EventObjectData;
import com.iz.sandbox.event.ObjectCreatedMessage;
import com.iz.sandbox.event.ObjectDeletedMessage;
import com.iz.sandbox.event.ObjectUpdatedMessage;
import com.iz.sandbox.model.ObjectEventDocument;
import com.iz.sandbox.model.ObjectInfo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.UUID;

@Mapper(componentModel = "spring", imports = UUID.class)
public interface ObjectEventTypeMapper {
    @Mappings({
            // mapping UUID to string to avoid string it as binary
            @Mapping(target = "objectId", expression = "java(source.getObjectId().toString())"),
            @Mapping(target = "principal", expression = "java(source.getEventData().getPrincipal())"),
            @Mapping(target = "publishedAt", expression = "java(source.getEventData().getPublishedAt())"),
            @Mapping(target = "data", source = "eventPayload"),
            @Mapping(target = "eventType", constant = "CREATE"),
    })
    ObjectEventDocument mapMessageDataToDocument(ObjectCreatedMessage source);

    @Mappings({
            // mapping UUID to string to avoid string it as binary
            @Mapping(target = "objectId", expression = "java(source.getObjectId().toString())"),
            @Mapping(target = "principal", expression = "java(source.getEventData().getPrincipal())"),
            @Mapping(target = "publishedAt", expression = "java(source.getEventData().getPublishedAt())"),
            @Mapping(target = "data", source = "eventPayload"),
            @Mapping(target = "eventType", constant = "UPDATE"),
    })
    ObjectEventDocument mapMessageDataToDocument(ObjectUpdatedMessage source);

    @Mappings({
            // mapping UUID to string to avoid string it as binary
            @Mapping(target = "objectId", expression = "java(source.getObjectId().toString())"),
            @Mapping(target = "principal", expression = "java(source.getEventData().getPrincipal())"),
            @Mapping(target = "publishedAt", expression = "java(source.getEventData().getPublishedAt())"),
            @Mapping(target = "eventType", constant = "DELETE"),
    })
    ObjectEventDocument mapMessageDataToDocument(ObjectDeletedMessage source);

    @Mappings({
            // mapping UUID to string to avoid string it as binary
            @Mapping(target = "id", expression = "java(source.getId().toString())")})
    ObjectInfo mapObjectDataToInfo(EventObjectData source);

    @Mappings({
            // mapping UUID to string to avoid string it as binary
            @Mapping(target = "objectId", expression = "java(UUID.fromString(source.getObjectId()))"),
    })
    ObjectEventData mapEventDocumentToDto(ObjectEventDocument source);

    @Mappings({
            // mapping UUID to string to avoid string it as binary
            @Mapping(target = "id", expression = "java(UUID.fromString(source.getId()))"),
    })
    ObjectData mapInfoToObjectData(ObjectInfo source);
}
