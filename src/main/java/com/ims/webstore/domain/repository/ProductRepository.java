package com.ims.webstore.domain.repository;

import com.ims.webstore.domain.Category;
import com.ims.webstore.domain.Product;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface ProductRepository {
    List<Product> getAllProducts();

    Product getProductById(String productId);

    List<Product> getProductsByCategory(Category category);

    Set<Product> getProductsByFilter(Map<String, List<String>> filterParams);

    List<Product> getProductsByManufacturer(String manufacturer);

    List<Product> getProductByPrice(Map<String, String> priceParams);

    void addProduct(Product product);
}
