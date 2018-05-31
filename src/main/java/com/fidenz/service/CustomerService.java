package com.fidenz.service;


import com.fidenz.dao.SearchFilter;
import com.fidenz.entity.Customers;

import java.util.List;

/**
 * @author Kalana on May, 2018
 */
public interface CustomerService {
    List<Customers> findAllCustomers(SearchFilter filter);
}
