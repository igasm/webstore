package com.ims.webstore.service;

import com.ims.webstore.domain.Category;
import com.ims.webstore.domain.Product;
import com.ims.webstore.domain.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class ProductServiceImpl implements ProductService{

  @Autowired
  ProductRepository productRepository;

  @Override
  public List<Product> getAllProducts() {
    return productRepository.getAllProducts();
  }

  @Override
  public List<Product> getProductsByCategory(String category) {
    Category enumCategory = Category.valueOf(category.toUpperCase());
    return productRepository.getProductsByCategory(enumCategory);
  }

  @Override
  public Set<Product> getProductsByFilter(Map<String, List<String>> filterParams) {
    return this.productRepository.getProductsByFilter(filterParams);
  }

  @Override
  public Product getProductById(String productId) {
    return this.productRepository.getProductById(productId);
  }

  @Override
  public List<Product> getProductsByManufacturer(String manufacturer) {
    return this.productRepository.getProductsByManufacturer(manufacturer);
  }

  @Override
  public List<Product> getProductsByProductCategoryPriceAndManufacturer(String productCategory, Map<String, String> priceParams, String manufacturer) {
    List<Product> byCategory = this.productRepository.getProductsByCategory(Category.valueOf(productCategory.toUpperCase()));
    List<Product> byPrice = this.productRepository.getProductByPrice(priceParams);
    List<Product> byManufacturer = this.productRepository.getProductsByManufacturer(manufacturer);

    byCategory.retainAll(byPrice);
    byCategory.retainAll(byManufacturer);

    return byCategory;
  }

  @Override
  public void addProduct(Product product) {
    this.productRepository.addProduct(product);
  }
}
