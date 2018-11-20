package com.ims.webstore.controller;

import com.ims.webstore.domain.Customer;
import com.ims.webstore.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

  @Autowired
  private CustomerService customerService;

  @RequestMapping(method = RequestMethod.GET)
  public List<Customer> list(){
    return customerService.getAllCustomers();
  }

}
