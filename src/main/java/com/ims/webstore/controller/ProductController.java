package com.ims.webstore.controller;

import com.ims.webstore.domain.Product;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigDecimal;

@Controller
public class ProductController {

  @RequestMapping("/products")
  public String list(Model model){
    Product iphone = new Product("P1234", "iPhone 5s", new BigDecimal(500));
    iphone.setDescription("Apple iPhone 5s, smartfon z 4-calowym wyświetlaczem");
    iphone.setCategory("Smart Phone");
    iphone.setManufacturer("Apple");
    iphone.setUnitsInStock(1000);
    model.addAttribute("product", iphone);
    return "products";
  }



}
