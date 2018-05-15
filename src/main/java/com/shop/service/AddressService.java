package com.shop.service;

import com.shop.model.Address;

import javax.persistence.EntityNotFoundException;

public interface AddressService {
    Address getByID(Long id) throws EntityNotFoundException;
}
