package com.shop.controller;

import com.shop.dto.AddressDto;
import com.shop.model.User;
import com.shop.service.DtoService;
import com.shop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
public class SettingsController {

    @Autowired
    UserService userService;

    @Autowired
    DtoService dtoService;

    @GetMapping("/user/settings")
    public String showSettings(Model model){
        model.addAttribute("newAddress",new AddressDto());
        return "userSettings";
    }

    @PostMapping("/user/changeEmail")
    public String changeEmail(@RequestParam("newMail") String newEmail,@RequestParam("emailPassword") String password, Principal principal){
        User userToUpdate = userService.findByUsername(principal.getName());
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        if(passwordEncoder.matches(password,userToUpdate.getPassword())){
            userToUpdate.setEmail(newEmail);
            userService.updateUser(userToUpdate);
            return "redirect:/user/settings";
        }
        else
            return "redirect:/user/settings?err";
    }

    @PostMapping("/user/addNewAddress")
    public String addNewAddress(@ModelAttribute("newAddress") AddressDto newAddress, Principal principal){
        User userToUpdate = userService.findByUsername(principal.getName());
        userToUpdate.getAddresses().add(dtoService.createAddressFromAddressDto(userToUpdate,newAddress));
        userService.updateUser(userToUpdate);
        return "redirect:/user/settings";
    }

    @PostMapping("/user/changePassword")
    public String changePassword(@RequestParam("newPassword") String newPassword,@RequestParam("oldPassword") String oldPassword,Principal principal){
        User userToUpdate = userService.findByUsername(principal.getName());
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        if(passwordEncoder.matches(oldPassword,userToUpdate.getPassword())){
            userToUpdate.setPassword(passwordEncoder.encode(newPassword));
            userService.updateUser(userToUpdate);
            return "redirect:/user/settings";
        }
        else
            return "redirect:/user/settings?err";
    }

}
