package com.vaadin.fastui.backend.repository;

import com.vaadin.fastui.backend.entity.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Integer> {

}
