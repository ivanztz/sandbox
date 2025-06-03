package com.iz.sandbox.config;

import com.iz.sandbox.event.ObjectChangedEvent;
import com.iz.sandbox.object.event.dto.ObjectData;
import com.iz.sandbox.object.event.dto.ObjectEventData;
import com.iz.sandbox.event.EventObjectData;
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
            @Mapping(target = "eventType", expression = "java(source.getEventData().getType().toString())"),
    })
    ObjectEventDocument mapMessageDataToDocument(ObjectChangedEvent source);


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
