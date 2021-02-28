package com.vaadin.fastui.backend.dao;

import com.vaadin.fastui.backend.entity.Customer;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class CustomerDaoImplementation implements CustomerDAO{

    // need to inject the session factory

    @Autowired
    private EntityManager entityManager;

    @Override
    @Transactional //
    public List<Customer> getCustomers() {
        entityManager.unwrap(Session.class);

        // create query
        TypedQuery<Customer> customerQuery = entityManager.createQuery("SELECT c FROM Customer c", Customer.class);

        // exectyre query and get result list
            List<Customer> customers = customerQuery.getResultList();
        // return results
        return customers;
    }
}
