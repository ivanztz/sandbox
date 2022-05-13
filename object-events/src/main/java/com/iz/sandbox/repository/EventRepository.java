package com.iz.sandbox.repository;

import com.iz.sandbox.model.ObjectEventDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface EventRepository extends MongoRepository<ObjectEventDocument, UUID> {
}
