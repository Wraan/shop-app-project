package com.shop.controller;

import com.shop.dto.AddressDto;
import com.shop.model.Address;
import com.shop.model.User;
import com.shop.service.AddressService;
import com.shop.service.DtoService;
import com.shop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
public class SettingsController {

    @Autowired
    UserService userService;

    @Autowired
    DtoService dtoService;

    @Autowired
    AddressService addressService;

    @GetMapping("/user/settings")
    public String showSettings(Model model,Principal principal){
        model.addAttribute("newAddress",new AddressDto());
        User user = userService.findByUsername(principal.getName());
        List<Address> addresses = user.getAddresses();
        model.addAttribute("addressessList", addresses);
        return "settings-panel";
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

    @PostMapping("/user/deleteAddress/{id}")
    public String deleteAddress(@PathVariable("id") long id, Principal principal)
    {
        User user = userService.findByUsername(principal.getName());
        Address address = addressService.getByID(id);
        System.out.println("Kappa");
        userService.deleteUserAddress(address,user);
        return "redirect:/user/settings";
    }
}
