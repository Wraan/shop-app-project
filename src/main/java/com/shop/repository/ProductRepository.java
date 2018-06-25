package com.shop.repository;

import com.shop.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Product findById(long id);

    @Query("select p from Product p where p.name like %:#{#productName}%")
    List<Product> findProductByName(@Param(value = "productName") String productName);

    @Query("select p from Product p where p.name like %:#{#productName}% and p.type like %:#{#productType}%")
    List<Product> findProductByNameAndCategory(@Param(value = "productName") String productName, @Param(value = "productType") String productType);
}