package com.vaadin.fastui.backend.repository;

import com.vaadin.fastui.backend.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CustomerRepo extends JpaRepository<Customer, Integer> {


}




