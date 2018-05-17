package com.shop.controller;

import com.shop.dto.RegistrationDto;
import com.shop.model.User;
import com.shop.service.DtoService;
import com.shop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.Arrays;

@Controller
public class RegisterController {

    @Autowired
    UserService userService;

    @Autowired
    DtoService dtoService;

    @GetMapping("/signUp")
    public String showSignUpPage(Model model) {
        model.addAttribute("register", new RegistrationDto());
        return "signUp";
    }

    @PostMapping("/signUp")
    public String signUpNewUser(@ModelAttribute("register") @Valid RegistrationDto registrationDto,
                                BindingResult result) {
        if(result.hasErrors()) {
            for(FieldError fieldError : result.getFieldErrors()){
                if(!fieldError.getField().split("\\.")[0].equals("addressDto"))
                    return "redirect:/signUp?err";
            }
            registrationDto.setAddressDto(null);
        }
        if(userService.register(dtoService.createUserFromRegistrationDto(registrationDto)) == null)
            return "redirect:/signUp?err";
        return "redirect:/";
    }
}
