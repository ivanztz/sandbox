package com.iz.sandbox.repository;

import com.iz.sandbox.entity.ObjectEntity;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;


@Repository
public interface ObjectRepository extends JpaRepository<ObjectEntity, UUID>, JpaSpecificationExecutor<ObjectEntity> {

    List<ObjectEntity> findAllByCreatedAtBetween(OffsetDateTime startDate, OffsetDateTime endDate);

    List<ObjectEntity> findAll(Specification<ObjectEntity> specification);
}
