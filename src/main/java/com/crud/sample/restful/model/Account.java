package com.crud.sample.restful.model;

import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

@Entity
@Table(name="ACCOUNT")
@Data
@Builder
@lombok.AllArgsConstructor
@lombok.NoArgsConstructor
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name__ac")
    private String name;

    @Column(name = "email__ac")
    private String email;

    @Column(name = "age__ac")
    private Integer age;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "ACCOUNTS_ADDRESSES",
            joinColumns = @JoinColumn(
                    name = "account_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "address_id", referencedColumnName = "id"))
    private Collection<Address> addresses;

    @CreationTimestamp
    @Column(name = "created__ac", updatable = false)
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date created;

    @UpdateTimestamp
    @Column(name = "updated__ac")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date updated;
}
