package com.shop.model;

import javax.persistence.*;

@Entity
@Table(name = "product_observations")
public class ProductSubscription {

    @Id
    @GeneratedValue
    @Column(name = "product_observation_id")
    private long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id")
    private Product product;

    public ProductSubscription(User user, Product product) {
        this.user = user;
        this.product = product;
    }

    public ProductSubscription() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}