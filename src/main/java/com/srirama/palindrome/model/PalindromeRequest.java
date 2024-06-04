package com.srirama.palindrome.model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;

public class PalindromeRequest {
    @NotEmpty(message = "Username cannot be empty")
    private String username;

    @NotEmpty(message = "Value cannot be empty")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Value must not contain spaces or numbers")
    private String value;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
