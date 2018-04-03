package com.ims.webstore.service;

import com.ims.webstore.domain.Customer;
import com.ims.webstore.domain.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

  @Autowired
  private CustomerRepository customerRepository;

  @Override
  public List<Customer> getAllCustomers() {
    return customerRepository.getAllCustomers();
  }
}
