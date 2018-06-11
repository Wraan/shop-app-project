package com.shop.service;

import com.shop.model.Specification;
import com.shop.repository.SpecificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SpecificationServiceImpl implements SpecificationService {

    @Autowired
    SpecificationRepository specificationRepository;

    public List<Specification> updateSpecificationsInDatabase(List<Specification> specifications){
        List<Specification> updatedSpecifications = new ArrayList<>();
        for(Specification spec : specifications){
            Specification updatedSpecification = specificationRepository.findByNameAndValue(spec.getName(), spec.getValue());
            if(updatedSpecification == null)
                updatedSpecification = specificationRepository.save(spec);
            updatedSpecifications.add(updatedSpecification);
        }
        return updatedSpecifications;
    }
}
