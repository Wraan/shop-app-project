package com.shop.controller;

import com.shop.dto.AddressDto;
import com.shop.model.User;
import com.shop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@Controller
public class SettingsController {

    @Autowired
    UserService userService;

    @GetMapping("/settings")
    public String showSettings(Model model){
        model.addAttribute("currentUser",new User());
        model.addAttribute("newAddress",new AddressDto());
        return "userSettings";
    }

    @PostMapping("/changeEmail")
    public String changeEmail(@ModelAttribute("currentUser") User currentUser, Principal principal){
        User userToUpdate = userService.findByUsername(principal.getName());
        userToUpdate.setEmail(currentUser.getEmail());
        //userService.updateUser(userToUpdate);
        return "redirect:/settings";
    }

    @PostMapping("/addNewAddress")
    public String addNewAddress(@ModelAttribute("newAddress") AddressDto newAddress, Principal principal){
        User userToUpdate = userService.findByUsername(principal.getName());
        return "redirect:/settings";
    }
}
