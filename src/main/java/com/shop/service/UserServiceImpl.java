package com.shop.service;

import com.shop.model.User;
import com.shop.model.security.UserRole;
import com.shop.repository.RoleRepository;
import com.shop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User register(User user) {
        user.setEnabled(true);
        user.setRegistrationDate(Calendar.getInstance());
        addUserRole(user);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public void addUserRole(User user) {
        user.setUserRoles(new HashSet<UserRole>(){{
            add(new UserRole(user, roleRepository.findByRoleName("ROLE_USER")));
        }});
    }
}
