package com.example.final_project.controller;

import com.example.final_project.entity.Country;
import com.example.final_project.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/country")
public class CountryController {
    private final CountryService countryService;

    @Autowired
    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping(path = "/{countryId}")
    public ResponseEntity<Country> getCountry(@PathVariable Integer countryId) {
        Country country = countryService.getCountry(countryId);
        return ResponseEntity.ok(country);
    }

    @PostMapping(path = "/create-country")
    public ResponseEntity<Country> addNewCountry(@RequestBody Country country) {
        countryService.addCountry(country);
        return ResponseEntity.ok(country);
    }

    @GetMapping(path = "/list-countries")
    public ResponseEntity<List<Country>> getCountries() {
        List<Country> countries = countryService.getCountries();
        return ResponseEntity.ok(countries);
    }
}