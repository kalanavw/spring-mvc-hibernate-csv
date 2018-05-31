package com.fidenz.manager;

import com.fidenz.entity.Customers;

import java.util.List;

/**
 * @author Kalana on May, 2018
 */
public interface CustomerManager {

    List<Customers> findAllCustomers();

    List<Customers> findAllCustomersById(String id);

    List<Customers> findAllCustomersByName(String name);

    List<Customers> findAllCustomersGroupByZipCode(String zipCode);
}
