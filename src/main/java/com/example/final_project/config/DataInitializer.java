package com.example.final_project.config;

// DataInitializer.java


import com.example.final_project.model.Role;
import com.example.final_project.repository.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private final RoleRepository roleRepository;

    public DataInitializer(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void run(String... args) {
        if (!roleRepository.existsById("ROLE_USER")) {
            roleRepository.save(new Role("ROLE_USER"));
        }
        if (!roleRepository.existsById("ROLE_MANAGER")) {
            roleRepository.save(new Role("ROLE_MANAGER"));
        }
        if (!roleRepository.existsById("ROLE_ADMIN")) {
            roleRepository.save(new Role("ROLE_ADMIN"));
        }
    }
}
