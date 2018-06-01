package com.shop.service;

import com.shop.dto.RegistrationDto;
import com.shop.model.User;

public interface DtoService{

    User createUserFromRegistrationDto(RegistrationDto registrationDto);
}
