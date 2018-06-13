package com.shop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {

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
}
