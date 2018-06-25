package com.shop.service;

import com.shop.model.Address;
import com.shop.model.Product;
import com.shop.model.User;

public interface UserService {

    User findByUsername(String username);
    User register(User user);
    User addAdminUser();
    void addProductSubscriptionToUser(Product product, User user);
    void deleteProductSubscriptionFromUser(Product product, User user);
    void deleteUserAddress(Address address, User user);
    User updateUser(User user);
    User changeBanStatus(User user);
}
