package com.shop.controller;

import com.shop.service.UserService;
import com.shop.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @Autowired
    UserService userService;
    @Autowired
    CartService cartService;

    @GetMapping("/")
    public String showHomePage() {
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
