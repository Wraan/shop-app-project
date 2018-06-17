package com.shop.service;

import com.shop.dto.RegistrationDto;
import com.shop.model.User;
import com.shop.model.security.Role;
import com.shop.model.security.UserRole;
import com.shop.repository.RoleRepository;
import com.shop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private RoleService roleService;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, RoleService roleService) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.roleService = roleService;
    }

    @Override
    public User register(User user) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        user.setEnabled(true);
        roleService.addUserRole(user);
        user.setPassword(encoder.encode(user.getPassword()));
        if(userRepository.findByUsername(user.getUsername()) != null || userRepository.findByEmail(user.getEmail()) != null)
            return null;
        return userRepository.save(user);
    }

    @Override
    public void addAdminUser() {
        Optional<User> admin = userRepository.findByUsername("admin");
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        if (!admin.isPresent()) {
            User user = new User(Calendar.getInstance(), "admin", encoder.encode("admin"), "admin@admin.com");
            user.setEnabled(true);
            Set<UserRole> userRoles = new HashSet<>();
            userRoles.add(new UserRole(user, roleService.findByRoleName("ROLE_USER")));
            userRoles.add(new UserRole(user, roleService.findByRoleName("ROLE_ADMIN")));
            user.setUserRoles(userRoles);
            userRepository.save(user);
        }
    }

    @Override
    public User updateUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User changeBanStatus(User user) {
        user.setBanned(!user.isBanned());
        user.setEnabled(!user.isEnabled());
        return updateUser(user);
    }

    @Override
    public User findByUsername(String username) {
        Optional<User> byUsername = userRepository.findByUsername(username);
        if (byUsername.isPresent()) {
            return byUsername.get();
        }
        return null;
    }


    @Override
    public User findByEmail(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isPresent()) {
            return user.get();
        }
        return null;
    }



    @Override
    public Long getIdFromUsername(String username) {
        Optional<User> byUsername = userRepository.findByUsername(username);
        if (byUsername.isPresent()) {
            return byUsername.get().getId();
        }
        return 0L;
    }
}
