package com.shop.controller;

import com.shop.dto.ProductDto;
import com.shop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping("/product")
    public String showProductPage() {
        return "product";
    }

    @GetMapping("/addProduct")
    public String showAddProductPage(Model model) {
        model.addAttribute("product", new ProductDto());
        return "addProductInfo";
    }

    @PostMapping("/addProduct")
    public String showAddProductSpecPage(@ModelAttribute("product") ProductDto product){
        productService.save(product);
        return "redirect:/";
    }
}
