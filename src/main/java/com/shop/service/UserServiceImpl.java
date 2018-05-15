package com.shop.service;

import com.shop.dto.RegistrationDto;
import com.shop.model.User;
import com.shop.model.security.UserRole;
import com.shop.repository.RoleRepository;
import com.shop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Calendar;
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
    public User createUserFromForm(RegistrationDto registrationDto) {
        return new User(Calendar.getInstance(), registrationDto.getUsername(), registrationDto.getPassword(), registrationDto.getEmail());
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
        user.setUserRoles(new HashSet<UserRole>() {{
            add(new UserRole(user, roleRepository.findByRoleName("ROLE_USER")));
        }});
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
