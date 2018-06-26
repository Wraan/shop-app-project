package com.shop.repository;

import com.shop.model.Cart;
import com.shop.model.Product;
import com.shop.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Calendar;
import java.util.List;

public interface CartRepository extends JpaRepository<Cart, Long> {
    List<Cart> findByUserAndBought(User user, boolean bought);
    Cart findById(long id);

    @Query("SELECT c FROM Cart c WHERE c.bought = 1 and c.purchaseDate BETWEEN :#{#monthBefore} and :#{#actualDate}")
    List<Cart> findCartsWithinOneMonth(@Param(value = "actualDate") Calendar actualDate, @Param(value = "monthBefore") Calendar monthBefore);


}
