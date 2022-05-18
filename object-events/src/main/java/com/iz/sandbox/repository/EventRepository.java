package com.iz.sandbox.repository;

import com.iz.sandbox.model.ObjectEventDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public interface EventRepository extends MongoRepository<ObjectEventDocument, UUID>, EventSearchRepository {

}
