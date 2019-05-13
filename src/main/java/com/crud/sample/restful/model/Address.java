package com.crud.sample.restful.model;

import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="ADDRESS")
@Data
@Builder
@lombok.AllArgsConstructor
@lombok.NoArgsConstructor
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "adress__ad")
    private String address;

    @CreationTimestamp
    @Column(name = "created__ad", updatable = false)
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date created;

    @UpdateTimestamp
    @Column(name = "updated__ad")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date updated;
}
