package com.shop.service;

import com.shop.dto.RegistrationDto;
import com.shop.model.User;

public interface UserService {

    User findByUsername(String username);

    User findByEmail(String email);

    User register(User user);

    Long getIdFromUsername(String username);

    User addAdminUser();

    User updateUser(User user);

    User changeBanStatus(User user);
}
