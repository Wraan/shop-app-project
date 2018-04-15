package com.shop.repository;

import com.shop.model.security.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

    Role findByRoleName(String roleName);
    Role findByRoleIdAndRoleName(long roleId, String roleName);

}
