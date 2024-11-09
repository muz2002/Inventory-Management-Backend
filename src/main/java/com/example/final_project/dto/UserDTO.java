package com.example.final_project.dto;

import com.example.final_project.entity.Country;

public class UserDTO {
    private Integer userId;
    private String name;
    private String username;
    private String email;
    private Country country;

    public UserDTO() {
    }

    public UserDTO(Integer userId, String name, String username, String email, Country country) {
        this.userId = userId;
        this.name = name;
        this.username = username;
        this.email = email;
        this.country = country;
    }

    // Getters and setters...

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }
}