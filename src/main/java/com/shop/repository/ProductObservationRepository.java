package com.shop.repository;

import com.shop.model.ProductObservation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductObservationRepository extends CrudRepository<ProductObservation, Long> {
    ProductObservation getById(Long id);

}
