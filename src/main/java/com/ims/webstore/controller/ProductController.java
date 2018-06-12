package com.ims.webstore.controller;

import com.ims.webstore.domain.Product;
import com.ims.webstore.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @RequestMapping
    public String list(Model model) {
        model.addAttribute("products", productService.getAllProducts());
        return "products";
    }

    @RequestMapping("/all")
    public String allProducts(Model model) {
        model.addAttribute("products", productService.getAllProducts());
        return "products";
    }

    @RequestMapping("/{category}")
    public String getProductsByCategory(Model model, @PathVariable("category") String productCategory) {
        model.addAttribute("products", productService.getProductsByCategory(productCategory));
        return "products";
    }

    @RequestMapping("/filter/{ByCriteria}")
    public String getProductsByFilter(
            @MatrixVariable(pathVar = "ByCriteria") Map<String, List<String>> filterParams,
            Model model) {
        model.addAttribute("products", productService.getProductsByFilter(filterParams));
        return "products";
    }

    @RequestMapping("/product")
    public String getProductById(@RequestParam("id") String productId, Model model) {
        model.addAttribute("product", productService.getProductById(productId));
        return "product";
    }

    @RequestMapping("/{category}/{price}")
    public String filterProducts(@PathVariable("category") String productCategory,
                                 @MatrixVariable(pathVar = "price") Map<String, String> filterParams,
                                 @RequestParam("manufacturer") String manufacturer,
                                 Model model) {
        model.addAttribute("products", productService.getProductsByProductCategoryPriceAndManufacturer(productCategory, filterParams, manufacturer));
        return "products";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String getAddNewProductForm(Model model){
        Product newProduct = new Product();
        model.addAttribute("newProduct", newProduct);
        return "addProduct";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String processAddNewProductForm(@ModelAttribute("newProduct") Product newProduct,
                                           BindingResult result, HttpServletRequest request){
        String[] suppressedFields = result.getSuppressedFields();

        if(suppressedFields.length > 0){
            throw new RuntimeException("Próba wiązania niedozwolonych pól: " + StringUtils.arrayToCommaDelimitedString(suppressedFields));
        }

//        MultipartFile productImage = newProduct.getProductImage();
//        String rootDirectory = request.getSession().getServletContext().getRealPath("/");
//        if (productImage!=null && !productImage.isEmpty()){
//            try {
//                productImage.transferTo(new File(rootDirectory+"resources\\static"+newProduct.getProductId() + ".jpg"));
//            } catch (Exception e) {
//                throw new RuntimeException("Niepowodzenie podczas próby zapisu obrazka produktu", e);
//            }
//        }

        productService.addProduct(newProduct);
        return "redirect:/products";
    }

    @InitBinder
    public void intialiseBinder(WebDataBinder binder){
        binder.setDisallowedFields("unitsInOrder", "discontinued");
    }

}
