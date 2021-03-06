package com.vaadin.fastui.backend.entity;


//import javax.persistence.*;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.util.List;


@Entity
@Table(name = "flights")
@Data
@NoArgsConstructor
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "flight_id")
    private Integer flightId;

    @Column(name = "from_city")
    @NotNull
    @NotEmpty
    private String fromCity;

    @Column(name = "to_city")
    @NotNull
    @NotEmpty
    private String toCity;

    @Column(name = "price")
    @NotNull
    @NotEmpty
    private double price;

    @Column(name = "start_date")
    @NotNull
    @NotEmpty
    private Date date;

    @Column(name = "seats_left")
    @NotNull
    @NotEmpty
    private int seatsLeft;

    @ManyToMany(fetch = FetchType.EAGER,
            cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(name="customer_flight",
            joinColumns = @JoinColumn(name="flight_id"),
            inverseJoinColumns = @JoinColumn(name="customer_id"))
    private List<Flight> flightList;

}
