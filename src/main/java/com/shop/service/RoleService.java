package com.shop.service;

import com.shop.model.User;
import com.shop.model.security.Role;

public interface RoleService {
    void addStartUpRoles();
    void addUserRole(User user);
    Role findByRoleName(String roleName);
}
