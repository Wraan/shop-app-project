package com.shop.service;

import com.shop.model.Specification;

import java.util.List;

public interface SpecificationService {
    List<Specification> updateSpecificationsInDatabase(List<Specification> specifications);
}
