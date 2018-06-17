package com.shop.service;

import com.shop.dto.ProductDto;
import com.shop.model.Product;

import java.util.List;

public interface ProductService {
    Product findById(long id);

    List<Product> getAll();

    Product findAllByCategory(String category);

    Product save(Product product);

    Product save(ProductDto productDto);

    void deleteProduct(Product product);
}
