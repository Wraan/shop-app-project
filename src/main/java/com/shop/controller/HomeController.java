package com.shop.controller;

import com.shop.model.Product;
import com.shop.service.ProductService;
import com.shop.service.UserService;
import com.shop.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    UserService userService;
    @Autowired
    CartService cartService;
    @Autowired
    ProductService productService;

    @GetMapping("/")
    public String showHomePage(Model model) {
        List<Product> newestProducts = productService.findNewestProducts(4);
        List<Product> popularProducts = cartService.findMostPopularProducts(4);

        model.addAttribute("newest", newestProducts);
        model.addAttribute("popular", popularProducts);
        return "index";
    }

    @GetMapping("/signIn")
    public String redirectToHome() {
        return "redirect:/";
    }

    @GetMapping("/find")
    public String findProducts(){
        return "find-products";
    }

    @GetMapping("/error")
    public String showError(){ return "error"; }

}
