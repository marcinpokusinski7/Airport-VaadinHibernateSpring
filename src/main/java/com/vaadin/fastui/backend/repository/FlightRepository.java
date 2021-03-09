package com.vaadin.fastui.backend.repository;

import com.vaadin.fastui.backend.entity.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Integer> {
    @Query("select c from Flight c " +
            "where lower(c.fromCity) like lower(concat('%', :searchTerm, '%')) " +
            "or lower(c.toCity) like lower(concat('%', :searchTerm, '%'))")
    List<Flight> search(@Param("searchTerm") String filterText);
}
