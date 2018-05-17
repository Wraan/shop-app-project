package com.shop.dto;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class AddressDto {

    @Length(max = 255)
    private String firstname;
    @Length(max = 255)
    private String lastname;
    @Length(max = 255)
    private String city;
    @Length(max = 255)
    private String street;
    @Pattern(regexp = "([0-9]{2}-[0-9]{3}$)")
    private String zipCode;
    @Pattern(regexp = "([0-9]{9}$)")
    private String phone;

    public AddressDto(@Length(max = 255) String firstname, @Length(max = 255) String lastname,
                      @Length(max = 255) String city, @Length(max = 255) String street,
                      @Pattern(regexp = "([0-9]{2}-[0-9]{3}$)") String zipCode,
                      @Pattern(regexp = "([0-9]{9}$)") String phone) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.city = city;
        this.street = street;
        this.zipCode = zipCode;
        this.phone = phone;
    }

    public AddressDto() {
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
