package com.shop.model;

import javax.persistence.*;

@Entity
@Table(name = "addresses")
public class Address {

    @Id
    @GeneratedValue
    @Column(name = "address_id")
    private Long id;

    @ManyToOne
    private User user;


    private String firstname;
    private String lastname;
    private String city;
    private String street;
    private String zipCode;
    private String phone;

    public Address(User user, String firstname, String lastname, String city,
                   String street, String zipCode, String phone) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.user = user;
        this.city = city;
        this.street = street;
        this.zipCode = zipCode;
        this.phone = phone;
    }

    public Address() {
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
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
