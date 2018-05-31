package com.fidenz.service;

import com.fidenz.entity.Customers;
import com.fidenz.manager.CustomerManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Kalana on May, 2018
 */
@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerManager customerManager;

    @Override
    public List<Customers> findAllCustomers() {
        return customerManager.findAllCustomers();
    }

    @Override
    public List<Customers> findAllCustomersById(String id) {
        return customerManager.findAllCustomersById(id);
    }

    @Override
    public List<Customers> findAllCustomersByName(String name) {
        return customerManager.findAllCustomersByName(name);
    }

    @Override
    public List<Customers> findAllCustomersGroupByZipCode(String zipCode) {
        return customerManager.findAllCustomersGroupByZipCode(zipCode);
    }
}
