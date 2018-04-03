package com.ims.webstore.service;

import com.ims.webstore.domain.Product;
import com.ims.webstore.domain.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService{

  @Autowired
  ProductRepository productRepository;

  @Override
  public List<Product> getAllProducts() {
    return productRepository.getAllProducts();
  }
}
