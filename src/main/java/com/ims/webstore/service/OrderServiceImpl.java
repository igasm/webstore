package com.ims.webstore.service;

import com.ims.webstore.domain.Product;
import com.ims.webstore.domain.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

  @Autowired
  private ProductRepository productRepository;

  @Override
  public void processOrder(String productId, int count) {
    Product product = productRepository.getProductById(productId);
    if(product.getUnitsInStock() < count){
      throw new IllegalArgumentException("Zbyt mało towaru. Obecna liczba sztuk w magazynie: " + product.getUnitsInStock());
    }
    //TODO for God's sake, not getter and setter. There should be methods to decrease and increase units in stock.
    product.setUnitsInStock(product.getUnitsInStock() - count);
  }
}
