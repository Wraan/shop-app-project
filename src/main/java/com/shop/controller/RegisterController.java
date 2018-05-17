package com.shop.controller;

import com.shop.dto.RegistrationDto;
import com.shop.model.User;
import com.shop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class RegisterController {

    @Autowired
    UserService userService;

    @GetMapping("/signUp")
    public String showSignUpPage(Model model) {
        model.addAttribute("register", new RegistrationDto());
        return "signUp";
    }

    @PostMapping("/signUp")
    public String signUpNewUser(@ModelAttribute("register") @Valid RegistrationDto registrationDto,
                                BindingResult result) {
        if(result.hasErrors())
            return "redirect:/signUp?err";

        userService.register(userService.createUserFromRegistrationDto(registrationDto));
        return "redirect:/";
    }
}
