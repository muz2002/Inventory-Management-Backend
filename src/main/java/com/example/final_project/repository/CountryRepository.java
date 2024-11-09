package com.example.final_project.repository;

import com.example.final_project.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<Country, Integer> {
    boolean existsByCountryName(String countryName);

}
