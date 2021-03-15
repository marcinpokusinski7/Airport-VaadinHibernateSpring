package com.vaadin.fastui.backend.entity;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;


@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "customer")
@Data
@NoArgsConstructor
public class Customer extends AbstractEntity implements Cloneable {



    @Column(name = "first_name")
    @NotNull
    @NotEmpty
    private String firstName;

    @Column(name = "last_name")
    @NotNull
    @NotEmpty
    private String lastName;

    @Column(name = "email")
    @NotNull
    @NotEmpty
    private String email;

    @Column(name = "phone_number")
    @NotEmpty
    @NotNull
    private String phoneNumber;

    @ManyToMany(fetch = FetchType.EAGER,
    cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(name="customer_flight",
    joinColumns = @JoinColumn(name="customer_id"),
    inverseJoinColumns = @JoinColumn(name="flight_id"))
    private List<Customer> customerList;

}
