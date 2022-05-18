package com.iz.sandbox.repository;

import com.iz.sandbox.model.ObjectEventDocument;

import java.time.OffsetDateTime;
import java.util.List;

public interface EventSearchRepository {
     List<ObjectEventDocument> findByCriteria(String objectId, OffsetDateTime startDate, OffsetDateTime endDate);
}
