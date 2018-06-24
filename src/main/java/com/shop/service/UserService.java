package com.shop.service;

import com.shop.dto.RegistrationDto;
import com.shop.model.Product;
import com.shop.model.ProductObservation;
import com.shop.model.User;

public interface UserService {

    User findByUsername(String username);

    User register(User user);

    User addAdminUser();

    void addProductSubscriptionToUser(Product product, User user);

    void deleteProductSubscriptionFromUser(Product product, User user);
}
