package com.shop.service;

import com.shop.dto.RegistrationDto;
import com.shop.model.User;
import com.shop.model.security.UserRole;
import com.shop.repository.RoleRepository;
import com.shop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User register(User user) {
        user.setEnabled(true);
        addUserRole(user);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        if(userRepository.findByUsername(user.getUsername()) != null || userRepository.findByEmail(user.getEmail()) != null)
            return null;
        return userRepository.save(user);
    }

    @Override
    public User updateUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User changeBanStatus(User user) {
        user.setBanned(!user.isBanned());
        return updateUser(user);
    }

    private void addUserRole(User user) {
        user.setUserRoles(new HashSet<UserRole>() {{
            add(new UserRole(user, roleRepository.findByRoleName("ROLE_USER")));
        }});
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
