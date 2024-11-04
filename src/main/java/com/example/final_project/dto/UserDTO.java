package com.example.final_project.dto;

public class UserDTO {
    private Integer userId;
    private String name;
    private String username;
    private String email;

    public UserDTO() {

    }

    public UserDTO(Integer userId, String name, String username, String email) {
        this.userId = userId;
        this.name = name;
        this.username = username;
        this.email = email;
    }

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
    // Constructors, getters, and setters...
}

