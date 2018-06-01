package com.shop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SettingsController {

    @GetMapping("/settings")
    public String showSettings(){
        return "settings-email";
    }

    @GetMapping("/settings/email")
    public String showEmailChange(){
        return "settings-email";
    }

    @GetMapping("/settings/password")
    public String showPasswordChange(){
        return "settings-password";
    }

    @GetMapping("/settings/address")
    public String showAddressManage(){
        return "settings-address";
    }
}
