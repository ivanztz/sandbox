package com.iz.sandbox.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.type.SqlTypes;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.*;

import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name = "objects")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public class ObjectEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String stringField;

    private Long intField;

    private Double decimalField;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "json_field", columnDefinition = "jsonb")
    private String jsonField;

    @CreationTimestamp
    private OffsetDateTime createdAt;

    @UpdateTimestamp
    private OffsetDateTime updatedAt;
}
