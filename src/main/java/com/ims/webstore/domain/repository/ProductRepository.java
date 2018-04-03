package com.ims.webstore.domain.repository;

import com.ims.webstore.domain.Product;

import java.util.List;

public interface ProductRepository {
  List<Product> getAllProducts();
  Product getProductById(String productId);
}
