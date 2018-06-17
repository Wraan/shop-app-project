package com.shop.controller;

import com.shop.components.Session;
import com.shop.dto.ProductDto;
import com.shop.model.Product;
import com.shop.model.Product;
import com.shop.model.ProductObservation;
import com.shop.service.ProductObservationService;
import com.shop.service.ProductService;
import com.shop.service.UserConnectionServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ProductController {

    ProductService productService;
    UserConnectionServices userConnectionServices;
    ProductObservationService productObservationService;

    @Autowired
    public ProductController(ProductService productService, UserConnectionServices userConnectionServices, ProductObservationService productObservationService) {
        this.productService = productService;
        this.userConnectionServices = userConnectionServices;
        this.productObservationService = productObservationService;
    }

    @GetMapping("/product")
    public String showProduct(@RequestParam("id") Long id, Model model, HttpServletRequest request) {

        Product product = productService.findById(id);
        if (product == null) {
            return "redirect:/products";
        }

        ProductObservation productObservation = new ProductObservation();

        productObservation.setProdId(id);
        productObservation.setUserConnection(userConnectionServices.getUserConnection());
        productObservation.getStartWatchTimestamp();

        productObservationService.save(productObservation);
        model.addAttribute(product);
        return "product";
    }
    @GetMapping("/products")
    public String showProducts(Model model) {
        model.addAttribute(productService.getAll());
        return "products";
    }
    @GetMapping("/products/{category}")
    public String showProductsByCategory(@PathVariable("category") String category,
                                         Model model) {
        model.addAttribute(productService.findAllByCategory(category));
        return "products";
    }

    @GetMapping("/admin/addProduct")
    public String showAddProductPage(Model model) {
        model.addAttribute("product", new ProductDto());
        return "add-product";
    }

    @PostMapping("/admin/addProduct")
    public String sendProductToDatabase(@Valid @ModelAttribute("product") ProductDto productDto, BindingResult result){
        if(result.hasErrors()){
            return "redirect:/admin/addProduct?err";
        }
        Product product = productService.save(productDto);
        return "redirect:/admin/addProduct";
    }

}
