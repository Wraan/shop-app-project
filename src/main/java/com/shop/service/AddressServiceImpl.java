package com.shop.service;

import com.shop.model.Address;
import com.shop.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    public AddressServiceImpl(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    AddressRepository addressRepository;

    public Address getByID(Long id) throws EntityNotFoundException {
        Optional<Address> delivery = addressRepository.findById(id);
        return delivery.orElseThrow(EntityNotFoundException::new);
    }


}
