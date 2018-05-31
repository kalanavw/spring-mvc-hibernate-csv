package com.fidenz.controller;

import com.fidenz.dao.SearchFilter;
import com.fidenz.entity.Customers;
import com.fidenz.service.CustomerService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author Kalana on May, 2018
 */
@Controller
@RequestMapping(value = "/customers/")
public class CustomerController {
    public static final Logger LOGGER = Logger.getLogger(CustomerController.class.getName());

    @Autowired
    private CustomerService customerService;

    @RequestMapping(value = "all", method = RequestMethod.GET)
    @ResponseBody
    public List<Customers> loadAllCustomers() {
        return customerService.findAllCustomers(null);
    }

    @RequestMapping(value = "search", method = RequestMethod.POST)
    @ResponseBody
    public List<Customers> loadAllCustomers(@RequestBody SearchFilter filter) {
        return customerService.findAllCustomers(filter);
    }
}
