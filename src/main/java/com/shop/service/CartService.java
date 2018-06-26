package com.shop.service;

import com.shop.model.Cart;
import com.shop.model.Product;
import com.shop.model.User;

import java.util.List;

public interface CartService {
    List<Cart> findBoughtByUser(User user);
    Cart findNotBoughtByUser(User user);
    Cart save(Cart cart);
    List<Product> getProductsFromCart(Cart cart);
    Cart findById(long id);

    Cart addProductToCart(Product product, Cart cart);

    Cart deleteProductFromCart(Product product, Cart cart);

    double getCartPrice(Cart cart);
}
