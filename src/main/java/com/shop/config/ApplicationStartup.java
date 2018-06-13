package com.shop.config;

import com.shop.model.User;
import com.shop.model.security.Role;
import com.shop.model.security.UserRole;
import com.shop.repository.RoleRepository;
import com.shop.repository.UserRepository;
import com.shop.service.RoleService;
import com.shop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

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