package com.vaadin.fastui.backend.service;

import com.vaadin.fastui.backend.entity.Customer;
import com.vaadin.fastui.backend.repository.CustomerRepository;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
@Data
@NoArgsConstructor
public class CustomerService {
    private static final Logger LOGGER = Logger.getLogger(CustomerService.class.getName());
    private CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    public List<Customer> findAll(String filterText) {
        if (filterText == null || filterText.isEmpty()){
            return customerRepository.findAll();
        }else{
            return customerRepository.search(filterText);
        }

    }

    public long count() {
        return customerRepository.count();

    }

    public void delete(Customer customer) {
        customerRepository.delete(customer);
    }

    public void save(Customer customer) {
        if (customer == null) {
            LOGGER.log(Level.SEVERE,
                    "Customer is null");
            return;
        }
        customerRepository.save(customer);
    }

    public Map<String, Integer> getStats() {
        HashMap<String, Integer> stats = new HashMap<>();
        findAll().forEach(customer ->
                stats.put(customer.getEmail(), customer.getCustomerId()));
        return stats;
    }

    }


