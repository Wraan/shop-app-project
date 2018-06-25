package com.shop.service;

import com.shop.dto.ProductDto;
import com.shop.model.Product;
import com.shop.model.ProductObservation;
import com.shop.model.User;

import java.util.List;

import java.util.List;

public interface ProductService {
    Product findById(long id);
    Product save(Product product);
    Product save(ProductDto productDto);
    ProductObservation findProductSubscriptionByUserAndProduct(User user, Product product);
    List<Product> findFollowedProductsByUser(User user);
    void deleteProduct(Product product);

    List<Product> searchProduct(String searchedProductName, String searchedProductCategory);
    List<Product> searchProductByCategory(String category);
}
