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

    @Query("select p from Product p where p.name like %:#{#productName}%")
    List<Product> findProductByName(@Param(value = "productName") String productName);

    @Query("select p from Product p where p.name like %:#{#productName}% and p.type like %:#{#productType}%")
    List<Product> findProductByNameAndCategory(@Param(value = "productName") String productName, @Param(value = "productType") String productType);

    @Query("select p from Product p where p.type like %:#{#productCategory}%")
    List<Product> findProductByCategory(@Param(value = "productCategory") String productCategory);
}
