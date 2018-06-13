package com.shop.service;

import com.shop.model.User;
import com.shop.model.security.Role;
import com.shop.model.security.UserRole;
import com.shop.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleRepository roleRepository;

    public void addStartUpRoles() {
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

    public void addUserRole(User user) {
        user.setUserRoles(new HashSet<UserRole>() {{
            add(new UserRole(user, roleRepository.findByRoleName("ROLE_USER")));
        }});
    }

    @Override
    public Role findByRoleName(String roleName) {
        return roleRepository.findByRoleName(roleName);
    }
}
