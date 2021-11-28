package com.apicitiestqi.apirestcities.states.repositories;

import com.apicitiestqi.apirestcities.states.entities.State;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StateRepository extends JpaRepository<State,Long> {
}
