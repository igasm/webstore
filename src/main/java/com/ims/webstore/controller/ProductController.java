package com.ims.webstore.controller;

import com.ims.webstore.domain.Product;
import com.ims.webstore.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @RequestMapping(method = RequestMethod.GET)
    public List<Product> list() {
        return productService.getAllProducts();
    }

    @RequestMapping(value = "/category/{category}", method = RequestMethod.GET)
    public List<Product> getProductsByCategory(@PathVariable("category") String productCategory) {
        return productService.getProductsByCategory(productCategory);
    }

    @RequestMapping(value = "/filter/{ByCriteria}", method = RequestMethod.GET)
    public List<Product> getProductsByFilter(@MatrixVariable(pathVar = "ByCriteria") Map<String, List<String>> filterParams) {
        return new ArrayList<>(productService.getProductsByFilter(filterParams));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Product getProductById(@PathVariable("id") String productId) {
        return productService.getProductById(productId);
    }

}
