package com.ims.webstore.domain.repository.impl;

import com.ims.webstore.domain.Category;
import com.ims.webstore.domain.Product;
import com.ims.webstore.domain.repository.ProductRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.nio.FloatBuffer;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Repository
public class InMemoryProductRepository implements ProductRepository {

    private List<Product> listOfProducts = new ArrayList<Product>();

    public InMemoryProductRepository() {
        Product iphone = new Product("P1234", "iPhone 5s", new BigDecimal(500));
        iphone.setDescription("Apple iPhone 5s, smartfon z 4-calowym ekranem o rozdzielczości 640x1136 i 8-megapikselowym aparatem");
        iphone.setCategory(Category.SMARTPHONE);
        iphone.setManufacturer("Apple");
        iphone.setUnitsInStock(1000);

        Product laptop_dell = new Product("P1235", "Dell Inspiron", new BigDecimal(700));
        laptop_dell.setDescription("Dell Inspiron, 14-calowy laptop (czarny) z procesorem Intel Core 3. generacji");
        laptop_dell.setCategory(Category.LAPTOP);
        laptop_dell.setManufacturer("Dell");
        laptop_dell.setUnitsInStock(1000);

        Product tablet_Nexus = new Product("P1236", "Nexus 7", new BigDecimal(300));
        tablet_Nexus.setDescription("Google Nexus 7 jest najlżejszym 7-calowym tabletem z 4-rdzeniowym procesorem Qualcomm SnapdragonTM S4 Pro");
        tablet_Nexus.setCategory(Category.TABLET);
        tablet_Nexus.setManufacturer("Google");
        tablet_Nexus.setUnitsInStock(1000);

        listOfProducts.add(iphone);
        listOfProducts.add(laptop_dell);
        listOfProducts.add(tablet_Nexus);
    }

    public List<Product> getAllProducts() {
        return listOfProducts;
    }

    public Product getProductById(String productId) {
        Optional<Product> productById =
                listOfProducts
                        .stream()
                        .filter(product -> (product != null)
                                && (product.getProductId() != null)
                                && (product.getProductId().equals(productId))
                        ).findFirst();
        productById.orElseThrow(() -> new IllegalArgumentException("Brak produktu o wskazanym id: " + productId));
        return productById.get();
    }

    @Override
    public List<Product> getProductsByCategory(Category category) {
        return listOfProducts
                .stream()
                .filter(product -> product.getCategory() == category)
                .collect(Collectors.toList());
    }

    //TODO not too much logic in repository??
    @Override
    public Set<Product> getProductsByFilter(Map<String, List<String>> filterParams) {
        Set<String> criterias = filterParams.keySet();
        List<Predicate<Product>> allOfPredicates = new ArrayList<>();
        //TODO use map instead of if, some pattern?? -> criterias as enums and Strategy pattern??
        if(criterias.contains("brand")){
            allOfPredicates.add(isProductOfBrand(filterParams.get("brand")));
        }
        if(criterias.contains("category")){
            allOfPredicates.add(isProductOfCategory(filterParams.get("category")));
        }

        //combing multiple predicates into one
        Predicate<Product> compositePredicate = allOfPredicates.stream().reduce(product -> true, Predicate::and);

        return this.listOfProducts.stream().filter(compositePredicate).collect(Collectors.toSet());
    }

    @Override
    public List<Product> getProductsByManufacturer(String manufacturer) {
        return this.listOfProducts
                .stream()
                .filter(product -> product.getManufacturer().equalsIgnoreCase(manufacturer))
                .collect(Collectors.toList());
    }

    @Override
    public List<Product> getProductByPrice(Map<String, String> priceParams) {
        BigDecimal low, high;
        if(!priceParams.containsKey("low")){
            low = BigDecimal.ZERO;
        }else{
            low = BigDecimal.valueOf(Float.parseFloat(priceParams.get("low")));
        }

        if(!priceParams.containsKey("high")){
            high = BigDecimal.valueOf(Integer.MAX_VALUE);
        }else{
            high = BigDecimal.valueOf(Float.parseFloat(priceParams.get("high")));
        }

        return this.listOfProducts
                .stream()
                .filter(product ->
                        (product.getUnitPrice().compareTo(low) > 0 ||  product.getUnitPrice().compareTo(low) == 0)
                        && (product.getUnitPrice().compareTo(high) < 0 || product.getUnitPrice().compareTo(low) == 0))
                .collect(Collectors.toList());
    }

    @Override
    public void addProduct(Product product) {
        this.listOfProducts.add(product);
    }

    public Predicate<Product> isProductOfBrand(List<String> brands){
        return product -> brands.contains(product.getManufacturer());
    }

    public Predicate<Product> isProductOfCategory(List<String> categories){
        Set<Category> searchedCategories
                = categories
                .stream()
                .map(category -> Category.valueOf(category.toUpperCase()))
                .collect(Collectors.toSet());

        return product -> searchedCategories.contains(product.getCategory());
    }
}
