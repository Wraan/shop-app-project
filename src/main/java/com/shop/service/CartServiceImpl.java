package com.shop.service;

import com.shop.model.Cart;
import com.shop.model.CartProduct;
import com.shop.model.Product;
import com.shop.model.User;
import com.shop.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
  
    public List<Product> findMostPopularProducts() {
        return countProducts(findCartsWithinOneMonth());
    }

    private List<Cart> findCartsWithinOneMonth(){
        Calendar actualDate = Calendar.getInstance();
        Calendar monthAgo = Calendar.getInstance();
        monthAgo.setTime(new Date());
        monthAgo.add(Calendar.MONTH, -1);
        List<Cart> resultSet = cartRepository.findCartsWithinOneMonth(actualDate,monthAgo);
        return resultSet;
    }

    private List<Product> countProducts(List<Cart> carts){
        List<Product> products = new ArrayList<>();
        List<Integer> productNumber = new ArrayList<>();
        for(Cart c : carts){
            List<Product> cartProducts = getProductsFromCart(c);
            for(Product p : cartProducts){
                if(!products.contains(p)){
                    products.add(p);
                    productNumber.add(1);
                }
                else{
                    int index = products.indexOf(p);
                    int numberToUpdate = productNumber.get(index) + 1;
                    productNumber.set(index,numberToUpdate);
                }
            }
        }
        doubleBubbleSort(products,productNumber);
        List<Product> popularProducts = mostPopularProducts(4,products);
        return popularProducts;
    }

    private void doubleBubbleSort(List<Product> products,List<Integer> productNumber){
        int n = productNumber.size();
        int temp;
        Product tempProduct;
        for(int i=0; i < n; i++){
            for(int j=1; j < (n-i); j++){
                if(productNumber.get(j-1) > productNumber.get(j)){
                    //swap elements
                    tempProduct = products.get(j-1);
                    temp = productNumber.get(j-1);

                    products.set(j-1,products.get(j));
                    productNumber.set(j-1,productNumber.get(j));

                    products.set(j,tempProduct);
                    productNumber.set(j,temp);
                }
            }
        }
    }

    private List<Product> mostPopularProducts(int number,List<Product> products){
        List<Product> mostPopularProducts = new ArrayList<>();
        if(!(number>products.size())){
            for(int i =1;i<number+1;i++){
                mostPopularProducts.add(products.get(products.size()-i));
            }
            return mostPopularProducts;
        }
        else
            return  products;
    }
}
