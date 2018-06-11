package com.shop.service;

import com.shop.dto.RegistrationDto;
import com.shop.dto.SpecificationDto;
import com.shop.model.Specification;
import com.shop.model.User;

import java.util.List;

public interface DtoService{

    User createUserFromRegistrationDto(RegistrationDto registrationDto);
    List<SpecificationDto> getListSpecificationDtoFromJson(String json);
    List<Specification> convertListSpecificationDtoToListSpecification(List<SpecificationDto> specificationDtoList);
}
