package com.shop.model;

import javax.persistence.*;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue
    @Column(name = "product_id")
    private long id;
    private double price;
    private int amount;
    private String name;
    private String producer;
    private String type;
    private String description;

    @Column(name = "adding_date")
    private Calendar addingDate;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<ProductSpecification> productSpecifications = new HashSet<>();

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Image> images = new HashSet<>();

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<ProductRate> rates = new HashSet<>();

    public Product() {
    }

    public long getId() {
        return id;
    }

    public Product(String name, double price, int amount, String type, String producer, String description,
                   Calendar addingDate) {
        this.price = price;
        this.amount = amount;
        this.name = name;
        this.producer = producer;
        this.type = type;
        this.description = description;
        this.addingDate = addingDate;
    }

    public void setId(long id) {
        this.id = id;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Calendar getAddingDate() {
        return addingDate;
    }

    public void setAddingDate(Calendar addingDate) {
        this.addingDate = addingDate;
    }

    public Set<ProductSpecification> getProductSpecifications() {
        return productSpecifications;
    }

    public void setProductSpecifications(Set<ProductSpecification> productSpecifications) {
        this.productSpecifications = productSpecifications;
    }

    public Set<Image> getImages() {
        return images;
    }

    public void setImages(Set<Image> images) {
        this.images = images;
    }

    public Set<ProductRate> getRates() {
        return rates;
    }

    public void setRates(Set<ProductRate> rates) {
        this.rates = rates;
    }
}
