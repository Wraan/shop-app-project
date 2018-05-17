package com.shop.dto;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class RegistrationDto {

    @NotBlank
    @Pattern(regexp = "([a-zA-Z0-9]{6,32}$)")
    private String username;
    @NotBlank
    @Pattern(regexp = "([.-^a-zA-Z0-9]{6,32}$)")
    private String password;
    @NotBlank
    @Pattern(regexp = ".+\\@.+\\..+")
    private String email;
    @Valid
    private AddressDto addressDto;

    public RegistrationDto(@NotBlank @Pattern(regexp = "([a-zA-Z0-9]{6,32}$)") String username,
                           @NotBlank @Pattern(regexp = "([.-^a-zA-Z0-9]{6,32}$)") String password,
                           @NotBlank @Email String email, @Valid AddressDto addressDto) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.addressDto = addressDto;
    }

    public RegistrationDto() {
        this.addressDto = new AddressDto();
    }

    public AddressDto getAddressDto() {
        return addressDto;
    }

    public void setAddressDto(@Valid AddressDto addressDto) {
        this.addressDto = addressDto;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
