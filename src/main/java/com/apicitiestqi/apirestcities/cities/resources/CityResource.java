package com.apicitiestqi.apirestcities.cities.resources;

import com.apicitiestqi.apirestcities.cities.entities.City;
import com.apicitiestqi.apirestcities.cities.repositories.CityRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/cities")
public class CityResource {
    private final CityRepository repository;

    public CityResource(final CityRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public Page<City> cities(final Pageable page) {
        return repository.findAll(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<City> GetById(@PathVariable(value = "id") long id)
    {
        Optional<City> city = repository.findById(id);
        if(city.isPresent())
            return new ResponseEntity<City>(city.get(), HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
