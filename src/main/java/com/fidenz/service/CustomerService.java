package com.fidenz.service;


import com.fidenz.entity.Customers;

import java.util.List;

/**
 * @author Kalana on May, 2018
 */
public interface CustomerService {

    List<Customers> findAllCustomers();

    List<Customers> findAllCustomersById(String id);

    List<Customers> findAllCustomersByName(String name);

    List<Customers> findAllCustomersGroupByZipCode(String zipCode);
}
