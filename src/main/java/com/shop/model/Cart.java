package com.shop.model;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.*;

@Entity
@Table(name = "carts")
public class Cart {

    @Id
    @GeneratedValue
    @Column(name = "cart_id")
    private long id;

    @Column(name = "purchase_date")
    private Calendar purchaseDate;

    @ManyToOne
    private User user;
    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<CartProduct> cartProducts = new ArrayList<>();
    private boolean bought;
    @ManyToOne
    private Address address;

    public Cart() {
    }

    public Cart(Calendar purchaseDate, User user) {
        this.purchaseDate = purchaseDate;
        this.user = user;
    }

    public Cart(User user) {
        this.user = user;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<CartProduct> getCartProducts() {
        return cartProducts;
    }

    public void setCartProducts(List<CartProduct> cartProducts) {
        this.cartProducts = cartProducts;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Calendar getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(Calendar purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isBought() {
        return bought;
    }

    public void setBought(boolean bought) {
        this.bought = bought;
    }

    public String getDateString(){
        Calendar cal = this.purchaseDate;
        cal.add(Calendar.DATE, 1);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        return format.format(cal.getTime());
    }
}
