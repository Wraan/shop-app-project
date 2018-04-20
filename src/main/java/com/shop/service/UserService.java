package com.shop.service;

import com.shop.model.User;

public interface UserService {

    User findByUsername(String username);
}
