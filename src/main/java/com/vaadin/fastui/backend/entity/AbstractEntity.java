package com.vaadin.fastui.backend.entity;

import lombok.Data;

import javax.persistence.*;

@MappedSuperclass
@Data
@Table(name ="customer")
public abstract class AbstractEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private Integer id;
}