package com.shop.service;

import com.shop.dto.AddressDto;
import com.shop.dto.RegistrationDto;
import com.shop.model.Address;
import com.shop.model.User;
import org.springframework.stereotype.Service;

import java.util.Calendar;

@Service
public class DtoServiceImpl implements DtoService {

    public User createUserFromRegistrationDto(RegistrationDto registrationDto) {
        User user = new User(Calendar.getInstance(), registrationDto.getUsername().trim(),
                registrationDto.getPassword().trim(), registrationDto.getEmail().trim());
        Address address = createAddressFromAddressDto(user, registrationDto.getAddressDto());
        if (address != null)
            user.getAddresses().add(address);
        return user;
    }

    private Address createAddressFromAddressDto(User user, AddressDto addressDto){
        if(addressDto == null)
            return null;
        return new Address(user, addressDto.getFirstname().trim(), addressDto.getLastname().trim(), addressDto.getCity().trim(),
                addressDto.getStreet().trim(), addressDto.getZipCode().trim(), addressDto.getPhone().trim());
    }
}
