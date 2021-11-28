package com.apicitiestqi.apirestcities.countries.repositories;

import com.apicitiestqi.apirestcities.countries.entities.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<Country, Long> {
}
