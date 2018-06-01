package com.shop.service;

import com.shop.dto.RegistrationDto;
import com.shop.model.User;

public interface UserService {

    User createUserFromForm(RegistrationDto registrationDto);

    User findByUsername(String username);

    User register(User user);
}
