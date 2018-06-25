package com.shop.config;

import com.shop.service.RoleService;
import com.shop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class ApplicationStartup {

    @Autowired
    UserService userService;
    @Autowired
    RoleService roleService;

    @EventListener(ApplicationReadyEvent.class)
    public void fillDatabaseWithData() {
        roleService.addStartUpRoles();
        userService.addAdminUser();
    }

}