package com.vaadin.fastui.backend.service;

import com.vaadin.fastui.backend.entity.Customer;
import com.vaadin.fastui.backend.repository.CustomerRepository;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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

//    public void getId(List<Customer> customerList){
//        for(Customer customer: customerList){
//           customerList(getId(customer));
//
//            }
//        }
    }

}
