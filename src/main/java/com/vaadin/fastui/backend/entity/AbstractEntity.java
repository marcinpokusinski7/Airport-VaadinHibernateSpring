package com.vaadin.fastui.backend.entity;


import lombok.Data;

import javax.persistence.*;

@MappedSuperclass
@Data
public abstract class AbstractEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

}
