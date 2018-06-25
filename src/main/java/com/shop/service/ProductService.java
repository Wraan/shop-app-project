package com.shop.service;

import com.shop.dto.ProductDto;
import com.shop.model.Product;

import java.util.List;

public interface ProductService {
    Product findById(long id);

    Product save(Product product);

    Product save(ProductDto productDto);

    void deleteProduct(Product product);

    List<Product> searchProduct(String searchedProductName, String searchedProductCategory);
}
