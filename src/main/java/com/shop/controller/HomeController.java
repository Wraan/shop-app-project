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

    @GetMapping("/product")
    public String showProductPage() {
        return "product";
    }

    //testing on these two
    @GetMapping("/addProduct")
    public String showAddProductPage() {
        return "addProduct";
    }

    @PostMapping("/addProduct")
    @ResponseBody
    public String addProduct(@RequestParam("json") String json) {
        return json;
    }

}
