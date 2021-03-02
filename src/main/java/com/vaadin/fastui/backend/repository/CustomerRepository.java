package com.vaadin.fastui.backend.repository;

import com.vaadin.fastui.backend.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CustomerRepository extends JpaRepository<Customer, Integer> { /// helps to provide jpa operations to database


}




