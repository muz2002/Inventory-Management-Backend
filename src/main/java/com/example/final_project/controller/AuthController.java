package com.example.final_project.controller;



import com.example.final_project.model.User;
import com.example.final_project.model.Role;
import com.example.final_project.repository.UserRepository;
import com.example.final_project.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // Registration endpoint
    @PostMapping("/register")
    public String registerUser(@RequestBody User user) {
        // Check if username is already taken
        if (userRepository.findByUsername(user.getUsername()) != null) {
            return "Username already exists";
        }

        // Encrypt password
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setEnabled(true);

        // Assign default role
        Role defaultRole = roleRepository.findById("ROLE_USER").orElse(new Role("ROLE_USER"));
        user.setRoles(Collections.singleton(defaultRole));

        // Save user
        userRepository.save(user);

        return "User registered successfully";
    }

    // Login endpoint (not strictly necessary with HTTP Basic, but useful for JWT)
    @PostMapping("/login")
    public String login() {
        // Authentication is handled by Spring Security
        return "Login successful";
    }
}

