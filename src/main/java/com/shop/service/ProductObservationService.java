package com.shop.service;

import com.shop.model.ProductObservation;
import com.shop.repository.ProductObservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductObservationService {
    @Autowired
    ProductObservationRepository productObservationRepository;

    public ProductObservation getById(Long id) {
        Optional<ProductObservation> productObservation = productObservationRepository.findById(id);
        if (productObservation.isPresent()) {
            return productObservation.get();
        }
        System.out.println("NO PRODUCT WITH ID: " + id);
        return null;
    }

    public void save(ProductObservation productObservation) {
        productObservationRepository.save(productObservation);
    }
}
