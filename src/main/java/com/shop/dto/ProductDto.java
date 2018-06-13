package com.shop.dto;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ProductDto {

    private List<String> images = new ArrayList<>();
    @NotNull
    private double price;
    @NotNull
    private int amount;
    @NotBlank
    @Length(max = 255)
    private String name;
    @NotBlank
    @Length(max = 255)
    private String producer;
    @NotBlank
    @Length(max = 255)
    private String type;
    @NotBlank
    private String description;
    @NotBlank
    private String jsonSpecifications;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public String getJsonSpecifications() {
        return jsonSpecifications;
    }

    public void setJsonSpecifications(String jsonSpecifications) {
        this.jsonSpecifications = jsonSpecifications;
    }
}
