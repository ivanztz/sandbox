package com.iz.sandbox.repository;

import com.iz.sandbox.model.ObjectEventDocument;

import java.time.Instant;
import java.util.List;

public interface EventSearchRepository {
     List<ObjectEventDocument> findByCriteria(String objectId, Instant startDate, Instant endDate);
}
