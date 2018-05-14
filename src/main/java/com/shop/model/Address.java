package com.shop.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Address {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private User user;

    private String city;
    private String street;
    private String zipCode;

    public Address(User user, String city, String street, String zipCode) {
        this.user = user;
        this.city = city;
        this.street = street;
        this.zipCode = zipCode;
    }

    public Address() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }
}
