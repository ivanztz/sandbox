package com.iz.sandbox.entity;

import com.vladmihalcea.hibernate.type.json.JsonType;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name = "objects")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@TypeDef(
        name = "json",
        typeClass = JsonType.class
)
public class ObjectEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String stringField;

    private Long intField;

    private Double decimalField;

    @Type(type = "json")
    @Column(name = "json_field", columnDefinition = "jsonb")
    private String jsonField;

    @CreationTimestamp
    private OffsetDateTime createdAt;

    @UpdateTimestamp
    private OffsetDateTime updatedAt;
}
