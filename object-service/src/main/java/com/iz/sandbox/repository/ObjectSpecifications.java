package com.iz.sandbox.repository;

import com.iz.sandbox.entity.ObjectEntity;
import org.springframework.data.jpa.domain.Specification;

import java.time.OffsetDateTime;

public class ObjectSpecifications {

    public static Specification<ObjectEntity> isCreatedAfter(final OffsetDateTime startDate) {
        return (root, query, builder) -> builder.greaterThan(root.get("createdAt"), startDate);
    }

    public static Specification<ObjectEntity> isCreatedBefore(final OffsetDateTime endDate) {
        return (root, query, builder) -> builder.lessThan(root.get("createdAt"), endDate);
    }
}
