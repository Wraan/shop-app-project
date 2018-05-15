package com.shop.config;

import com.shop.model.security.Role;
import com.shop.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class ApplicationStartup {

    @Autowired
    private RoleRepository roleRepository;

    @EventListener(ApplicationReadyEvent.class)
    public void checkoutRoles() {
        Role admin = roleRepository.findByRoleIdAndRoleName(1, "ROLE_ADMIN");
        Role user = roleRepository.findByRoleIdAndRoleName(2, "ROLE_USER");
        if (admin == null || user == null) {
            roleRepository.deleteAll();
            roleRepository.saveAll(new ArrayList<Role>() {{
                add(new Role(1, "ROLE_ADMIN"));
                add(new Role(2, "ROLE_USER"));
            }});
        }


    }
}