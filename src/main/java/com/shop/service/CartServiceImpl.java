package com.shop.service;

import com.shop.model.Cart;
import com.shop.model.CartProduct;
import com.shop.model.Product;
import com.shop.model.User;
import com.shop.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cartRepository;

    public List<Cart> findBoughtByUser(User user){
        return cartRepository.findByUserAndBought(user, true);
    }

    public Cart findNotBoughtByUser(User user){
        List<Cart> carts = cartRepository.findByUserAndBought(user, false);
        if(carts.size() == 1)
            return carts.get(0);
        else
            return null;
    }

    public Cart save(Cart cart){
        return cartRepository.save(cart);
    }

    public List<Product> getProductsFromCart(Cart cart){
        List<Product> output = new ArrayList<>();
        for(CartProduct cartProd : cart.getCartProducts()){
            output.add(cartProd.getProduct());
        }
        return output;
    }

    public Cart findById(long id) {
        return cartRepository.findById(id);
    }

    @Override
    public Cart addProductToCart(Product product, Cart cart) {
        Cart newCart = cartRepository.findById(cart.getId());
        newCart.getCartProducts().add(new CartProduct(product,newCart));
        return cartRepository.save(newCart);
    }

    @Override
    public Cart deleteProductFromCart(Product product, Cart cart) {
        Cart newCart = cartRepository.findById(cart.getId());
        CartProduct toDeletion = null;
        for(CartProduct ca : newCart.getCartProducts()){
            if(ca.getProduct().getId() == product.getId()){
                toDeletion = ca;
                break;
            }
        }
        if(toDeletion != null)
            newCart.getCartProducts().remove(toDeletion);

        return cartRepository.save(newCart);
    }

    @Override
    public double getCartPrice(Cart cart) {
        Cart newCart = findById(cart.getId());
        double price = 0;
        List<Product> products = getProductsFromCart(newCart);
        for(Product product : products){
            price += product.getPrice();
        }
        return price;
    }
}
