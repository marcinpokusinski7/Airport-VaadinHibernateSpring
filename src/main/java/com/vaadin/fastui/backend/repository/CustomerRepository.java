package com.vaadin.fastui.backend.repository;

import com.vaadin.fastui.backend.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    @Query("select c from Customer c " +
            "where lower(c.firstName) like lower(concat('%', :searchTerm, '%')) " +
            "or lower(c.lastName) like lower(concat('%', :searchTerm, '%'))") // search in customer table, makes all
    //characters lower case and compare to themselves, checks if the last name or first name matches the query
    List<Customer> search(@Param("searchTerm") String filterText); /// helps to provide jpa operations to database


}




