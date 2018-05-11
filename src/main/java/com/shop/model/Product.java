package com.shop.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="products")
public class Product {

    @Id
    @GeneratedValue
    @Column(name = "product_id")
    private long id;

    @NotBlank
    private String name;

    @NotBlank
    private String producer;

    @NotBlank
    private String type;

    @NotNull
    private double price;

    @NotNull
    private int amount;

    @Lob
    @NotBlank
    private String description;

    @NotNull
    @Column(name = "adding_date")
    private Calendar addingDate;

    @OneToMany(mappedBy="product", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<ProductSpecification> productSpecifications = new HashSet<>();

    @OneToMany(mappedBy="product", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Image> images = new HashSet<>();

    @OneToMany(mappedBy="product", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<ProductRate> rates = new HashSet<>();

    public Product() {}

    public Product(@NotBlank String name, @NotBlank String producer, @NotBlank String type, @NotNull double price,
                   @NotNull int amount, @NotBlank String description, @NotNull Calendar addingDate) {
        this.name = name;
        this.producer = producer;
        this.type = type;
        this.price = price;
        this.amount = amount;
        this.description = description;
        this.addingDate = addingDate;
    }

    public long getId() {
        return id;
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
