package com.iz.sandbox.repository.impl;

import com.iz.sandbox.model.ObjectEventDocument;
import com.iz.sandbox.repository.EventSearchRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;

@Repository
@AllArgsConstructor
public class EventSearchRepositoryImpl implements EventSearchRepository {
    private final MongoTemplate mongoTemplate;

    @Override
    public List<ObjectEventDocument> findByCriteria(String objectId, Instant startDate, Instant endDate) {
        final Query query = new Query();
        if (objectId != null) {
            query.addCriteria(Criteria.where("objectId").is(objectId));
        }

        if (startDate != null) {
            query.addCriteria(Criteria.where("publishedAt").gt(startDate));
        }

        if (endDate != null) {
            query.addCriteria(Criteria.where("publishedAt").lt(endDate));
        }

        return mongoTemplate.find(query, ObjectEventDocument.class);
    }
}
