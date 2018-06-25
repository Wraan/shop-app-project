package com.shop.service;

import com.shop.model.Product;
import com.shop.model.ProductObservation;
import com.shop.model.User;
import com.shop.model.security.UserRole;
import com.shop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleService roleService;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private ProductService productService;

    @Override
    public User register(User user) {
        user.setEnabled(true);
        roleService.addUserRole(user);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        if(userRepository.findByUsername(user.getUsername()) != null || userRepository.findByEmail(user.getEmail()) != null)
            return null;
        return userRepository.save(user);
    }

    @Override
    public User addAdminUser() {
        User admin = userRepository.findByUsername("admin");
        if (admin == null) {
            admin = new User(Calendar.getInstance(), "admin", passwordEncoder.encode("admin"), "admin@admin.com");
            admin.setEnabled(true);
            Set<UserRole> userRoles = new HashSet<>();
            userRoles.add(new UserRole(admin, roleService.findByRoleName("ROLE_USER")));
            userRoles.add(new UserRole(admin, roleService.findByRoleName("ROLE_ADMIN")));
            admin.setUserRoles(userRoles);
            admin = userRepository.save(admin);
        }
        return admin;
    }

    @Override
    public void addProductSubscriptionToUser(Product product, User user) {
        ProductObservation productObservation = productService.findProductSubscriptionByUserAndProduct(user,product);
        if(productObservation == null){
            productObservation = new ProductObservation(user,product);
            user.getProductObservations().add(productObservation);
            userRepository.save(user);
        }
    }

    @Override
    public void deleteProductSubscriptionFromUser(Product product, User user) {
        ProductObservation productObservation = productService.findProductSubscriptionByUserAndProduct(user,product);
        if(productObservation != null){
            user.getProductObservations().remove(productObservation);
            userRepository.save(user);
        }
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
        return userRepository.findByUsername(username);
    }
}
