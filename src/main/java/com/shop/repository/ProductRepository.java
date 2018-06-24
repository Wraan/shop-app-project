package com.shop.repository;

import com.shop.model.Product;
import com.shop.model.ProductObservation;
import com.shop.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Product findById(long id);

    @Query("select po from ProductObservation po where po.user.id = :#{#user.id} and po.product.id = :#{#product.id}")
    ProductObservation findProductObservationByUserAndProduct(@Param("user") User user, @Param("product") Product product);

    @Query("select po from ProductObservation po where po.user.id = :#{#user.id}")
    List<ProductObservation> findProductObservationsByUser(@Param("user") User user);
}
