package com.shop.controller;

import com.shop.dto.ProductDto;
import com.shop.model.Product;
import com.shop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@Controller
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping("/product/{id}")
    public String showProductPage(@PathVariable("id") long id, Model model) {
        Product product = productService.findById(id);
        if(product == null)
            return "redirect:/";

        model.addAttribute("product", product);
        return "product";
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
        return "redirect:/";
    }

}
