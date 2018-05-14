package com.shop.dto;

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
    @Email
    private String email;

    public RegistrationDto(@NotBlank @Pattern(regexp = "([a-zA-Z0-9]{6,32}$)") String username,
                           @NotBlank @Pattern(regexp = "([.-^a-zA-Z0-9]{6,32}$)") String password,
                           @NotBlank @Email String email) {
        this.username = username;
        this.password = password;
        this.email = email;
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
