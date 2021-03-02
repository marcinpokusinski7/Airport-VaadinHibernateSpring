package com.vaadin.fastui.backend.service;

import com.vaadin.fastui.backend.repository.CustomerRepository;
import lombok.Data;
import org.springframework.stereotype.Service;

@Service
@Data
public class CustomerRepoImplementation  {
    private CustomerRepository customerRepository;




}
