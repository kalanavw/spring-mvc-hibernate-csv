package com.fidenz.controller;

import com.fidenz.entity.Customers;
import com.fidenz.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Kalana on May, 2018
 */
@Controller
@RequestMapping(value = "/customers/")
@CrossOrigin
public class CustomerAPI {

    @Autowired
    private CustomerService customerService;

    @RequestMapping(value = "all", method = RequestMethod.GET)
    @ResponseBody
    public List<Customers> loadAllCustomers() {
        return customerService.findAllCustomers();
    }

    @RequestMapping(value = "searchById/{id}", method = RequestMethod.POST)
    @ResponseBody
    public List<Customers> loadAllCustomersById(@PathVariable("id") String id) {
        return customerService.findAllCustomersById(id);
    }

    @RequestMapping(value = "searchByName/{name}", method = RequestMethod.GET)
    @ResponseBody
    public List<Customers> loadAllCustomersByName(@PathVariable("name") String name) {
        return customerService.findAllCustomersByName(name);
    }

    @RequestMapping(value = "groupByZipCode/{zipCode}", method = RequestMethod.POST)
    @ResponseBody
    public List<Customers> loadAllCustomersGroupByZipCode(@PathVariable("zipCode") String zipCode) {
        return customerService.findAllCustomersGroupByZipCode(zipCode);
    }
}
