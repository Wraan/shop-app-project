package com.shop.controller;

import com.shop.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController{

    @GetMapping("/")
    public String sayHello(){
        return "Hello!";
    }

    @GetMapping("/api/user/{id}")
    public User showUser(@PathVariable long id){
        return new User("user", "password");
    }
}
