package com.shop.model;

import javax.persistence.*;

@Entity
@Table(name = "cart_products")
public class CartProduct {

    @Id
    @GeneratedValue
    @Column(name = "cart_product_id")
    private long id;

    @ManyToOne(optional=false)
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne(optional=false)
    @JoinColumn(name = "cart_id")
    private Cart cart;

    public CartProduct() {
    }

    public CartProduct(Product product, Cart cart) {
        this.product = product;
        this.cart = cart;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }
}
