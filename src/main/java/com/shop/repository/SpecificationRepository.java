package com.shop.repository;

import com.shop.model.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpecificationRepository extends JpaRepository<Specification, Long> {
    Specification findByNameAndValue(String name, String value);
}
