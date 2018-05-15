package com.shop.dto;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class DeliveryDto {

    public DeliveryDto(@NotBlank @Length(max = 255) String city, @NotBlank @Length(max = 255) String street, @NotBlank @Pattern(regexp = "([0-9]{2}-[0-9]{3}$)") String zipCode) {
        this.city = city;
        this.street = street;
        this.zipCode = zipCode;
    }

    @NotBlank
    @Length(max = 255)
    private String city;

    @NotBlank
    @Length(max = 255)
    private String street;

    @NotBlank
    @Pattern(regexp = "([0-9]{2}-[0-9]{3}$)")
    private String zipCode;
}
