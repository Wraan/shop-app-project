package com.shop.service;

import com.shop.dto.RegistrationDto;
import com.shop.model.User;

public interface UserService {

    User findByUsername(String username);

    User register(User user);

    User addAdminUser();

    User updateUser(User user);

    User changeBanStatus(User user);

}
