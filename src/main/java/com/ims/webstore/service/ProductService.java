package com.ims.webstore.service;

import com.ims.webstore.domain.Product;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface ProductService {

    List<Product> getAllProducts();

    List<Product> getProductsByCategory(String category);

    Set<Product> getProductsByFilter(Map<String, List<String>> filterParams);

    Product getProductById(String productId);

    List<Product> getProductsByManufacturer(String manufacturer);

    List<Product> getProductsByProductCategoryPriceAndManufacturer(String productCategory, Map<String,String> filterParams, String manufacturer);

    void addProduct(Product product);
}
