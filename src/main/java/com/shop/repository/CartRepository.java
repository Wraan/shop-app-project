package com.shop.repository;

import com.shop.model.Cart;
import com.shop.model.Product;
import com.shop.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart, Long> {
    List<Cart> findByUserAndBought(User user, boolean bought);
    Cart findById(long id);
}
