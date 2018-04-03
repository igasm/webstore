package com.ims.webstore.domain.repository;

import com.ims.webstore.domain.Customer;

import java.util.List;

public interface CustomerRepository {

  List<Customer> getAllCustomers();

}
