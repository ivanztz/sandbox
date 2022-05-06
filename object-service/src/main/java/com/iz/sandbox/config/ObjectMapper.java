package com.iz.sandbox.config;


import com.iz.sandbox.dto.ObjectData;
import com.iz.sandbox.dto.ObjectDataRequest;
import com.iz.sandbox.entity.ObjectEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface ObjectMapper {
    @Mappings({
            @Mapping(target = "id", source = "id"),
            @Mapping(target = "stringField", source = "stringField"),
            @Mapping(target = "intField", source = "intField"),
            @Mapping(target = "decimalField", source = "decimalField"),
            @Mapping(target = "jsonField", source = "jsonField"),
            @Mapping(target = "createdAt", source = "createdAt"),
            @Mapping(target = "updatedAt", source = "updatedAt"),})
    ObjectData mapEntityToDto(ObjectEntity entity);

    @Mappings({
            @Mapping(target = "stringField", source = "stringField"),
            @Mapping(target = "intField", source = "intField"),
            @Mapping(target = "decimalField", source = "decimalField"),
            @Mapping(target = "jsonField", source = "jsonField"),})
    ObjectEntity mapRequestToEntity(ObjectDataRequest request);

    @Mappings({
            @Mapping(target = "stringField", source = "stringField"),
            @Mapping(target = "intField", source = "intField"),
            @Mapping(target = "decimalField", source = "decimalField"),
            @Mapping(target = "jsonField", source = "jsonField"),})
    void mapRequestToEntity(ObjectDataRequest source, @MappingTarget ObjectEntity target);

    @Mappings({
            @Mapping(target = "id", source = "id"),
            @Mapping(target = "stringField", source = "stringField"),
            @Mapping(target = "intField", source = "intField"),
            @Mapping(target = "decimalField", source = "decimalField"),
            @Mapping(target = "jsonField", source = "jsonField"),
            @Mapping(target = "createdAt", source = "createdAt"),
            @Mapping(target = "updatedAt", source = "updatedAt"),})
    ObjectEntity mapDtoToEntity(ObjectData dto);
}
