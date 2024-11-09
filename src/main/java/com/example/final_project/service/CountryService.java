package com.example.final_project.service;

import com.example.final_project.entity.Country;
import com.example.final_project.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryService {
    private final CountryRepository countryRepository;

    @Autowired
    public CountryService(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    public List<Country> getCountries() {
        return countryRepository.findAll();
    }

    public void addCountry(Country country) {
        if (!countryRepository.existsByCountryName(country.getCountryName())) {
            countryRepository.save(country);
        } else {
            throw new DataIntegrityViolationException("Country already exists");
        }
    }


    public Country getCountry(Integer countryId) {
        return countryRepository.findById(countryId)
                .orElseThrow(() -> new RuntimeException("Country not found"));
    }
}