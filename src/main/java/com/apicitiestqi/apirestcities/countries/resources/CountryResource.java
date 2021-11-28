package com.apicitiestqi.apirestcities.countries.resources;

import com.apicitiestqi.apirestcities.countries.entities.Country;
import com.apicitiestqi.apirestcities.countries.repositories.CountryRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
public class CountryResource {
    private final CountryRepository repository;

    public CountryResource(final CountryRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/countries")
    public List<Country> CountryList() {
        return repository.findAll();
    }

    @GetMapping("/countries/{id}")
    public ResponseEntity<Country> GetById(@PathVariable(value = "id") long id)
    {
        Optional<Country> country = repository.findById(id);
        if(country.isPresent())
            return new ResponseEntity<Country>(country.get(), HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/countries")
    public Country NewCountry(@Valid @RequestBody Country country)
    {
        return repository.save(country);
    }

    @PutMapping("/countries/{id}")
    public ResponseEntity<Country> PutCountry(@PathVariable(value = "id") long id, @Valid @RequestBody Country newCountry)
    {
        Optional<Country> oldCountry = repository.findById(id);
        if(oldCountry.isPresent()){
            Country country = oldCountry.get();
            country.setName(newCountry.getName());
            country.setPortugueseName(newCountry.getPortugueseName());
            country.setCode(newCountry.getCode());
            country.setBacen(newCountry.getBacen());
            repository.save(country);
            return new ResponseEntity<Country>(country, HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);}

    @DeleteMapping("/countries/{id}")
    public ResponseEntity<Object> DeleteCountry(@PathVariable(value = "id") long id)
    {
        Optional<Country> country = repository.findById(id);
        if(country.isPresent()){
            repository.delete(country.get());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

}

