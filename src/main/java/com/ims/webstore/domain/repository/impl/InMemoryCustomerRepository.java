package com.ims.webstore.domain.repository.impl;

import com.ims.webstore.domain.Customer;
import com.ims.webstore.domain.repository.CustomerRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Repository
public class InMemoryCustomerRepository implements CustomerRepository {

  List<Customer> customers;

  public InMemoryCustomerRepository() {
    this.customers = new ArrayList<>();

    Customer adam = new Customer();
    adam.setAddress("ul. Lea 88, 20-058 Kraków");
    adam.setCustomerId("c1");
    adam.setName("Adam Kowalski");
    adam.setNoOfOrdersMade(0);

    Customer tomek = new Customer();
    tomek.setAddress("ul. Bora-Komorowskiego 10/88, Kraków");
    tomek.setCustomerId("c2");
    tomek.setName("Tomek Nowak");
    tomek.setNoOfOrdersMade(0);

    customers.add(adam);
    customers.add(tomek);
  }

  @Override
  public List<Customer> getAllCustomers() {
    return customers;
  }
}
