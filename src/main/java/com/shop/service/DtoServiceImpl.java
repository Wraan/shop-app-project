package com.shop.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shop.dto.AddressDto;
import com.shop.dto.RegistrationDto;
import com.shop.dto.SpecificationDto;
import com.shop.model.Address;
import com.shop.model.Specification;
import com.shop.model.User;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

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

    public Address createAddressFromAddressDto(User user, AddressDto addressDto){
        if(addressDto == null)
            return null;
        return new Address(user, addressDto.getFirstname().trim(), addressDto.getLastname().trim(), addressDto.getCity().trim(),
                addressDto.getStreet().trim(), addressDto.getZipCode().trim(), addressDto.getPhone().trim());
    }

    public List<SpecificationDto> getListSpecificationDtoFromJson(String json){
        List<SpecificationDto> specificationDtoList = null;
        try {
            SpecificationDto[] specArray = new ObjectMapper().readValue(json, SpecificationDto[].class);
            specificationDtoList = Arrays.asList(specArray);
        }
        catch(Exception e){
        }
        return specificationDtoList;
    }

    @Override
    public List<Specification> convertListSpecificationDtoToListSpecification(List<SpecificationDto> specificationDtoList) {
        List<Specification> specificationList = new ArrayList<>(specificationDtoList.size());
        specificationDtoList.sort((Comparator.comparing(SpecificationDto::getNumber)));
        for(SpecificationDto specDto : specificationDtoList){
            Specification specification = new Specification();
            specification.setName(specDto.getName());
            specification.setValue(specDto.getValue());
            specificationList.add(specification);
        }
        return specificationList;
    }
}
