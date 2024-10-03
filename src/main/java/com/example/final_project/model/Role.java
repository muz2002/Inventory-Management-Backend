package com.example.final_project.model;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "roles")
public class Role {

    @Id
    @Column(length = 50)
    private String name; // e.g., "ROLE_ADMIN", "ROLE_MANAGER", "ROLE_USER"

    @ManyToMany(mappedBy = "roles")
    private Set<User> users;

    // Constructors, getters, setters
    public Role() {}

    public Role(String name, Set<User> users) {
        this.name = name;
        this.users = users;
    }

    public Role(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }
}

