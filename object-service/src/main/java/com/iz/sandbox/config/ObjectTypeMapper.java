package com.iz.sandbox.config;


import com.iz.sandbox.entity.ObjectEntity;
import com.iz.sandbox.event.EventObjectData;
import com.iz.sandbox.object.dto.ObjectData;
import com.iz.sandbox.object.dto.ObjectDataRequest;
import com.iz.sandbox.object.dto.ObjectEventData;
import com.iz.sandbox.object.dto.ObjectEventDataListResponse;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ObjectTypeMapper {
    ObjectData mapEntityToDto(ObjectEntity entity);

    EventObjectData mapEntityToEventDto(ObjectEntity objectEntity);

    ObjectEntity mapRequestToEntity(ObjectDataRequest request);

    void mapRequestToEntity(ObjectDataRequest source, @MappingTarget ObjectEntity target);

    ObjectEntity mapDtoToEntity(ObjectData dto);

    ObjectEventDataListResponse mapEventDataList(com.iz.sandbox.object.event.dto.ObjectEventDataListResponse source);

    ObjectEventData mapEventData(com.iz.sandbox.object.event.dto.ObjectEventData source);
}
