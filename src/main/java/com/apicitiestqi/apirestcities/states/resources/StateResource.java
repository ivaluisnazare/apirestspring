package com.apicitiestqi.apirestcities.states.resources;

import com.apicitiestqi.apirestcities.countries.entities.Country;
import com.apicitiestqi.apirestcities.states.entities.State;
import com.apicitiestqi.apirestcities.states.repositories.StateRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/states")
public class StateResource {
    private final StateRepository repository;

    public StateResource(final StateRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<State> states() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<State> GetById(@PathVariable(value = "id") long id)
    {
        Optional<State> state = repository.findById(id);
        if(state.isPresent())
            return new ResponseEntity<State>(state.get(), HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public State NewState(@Valid @RequestBody State state)
    {
        return repository.save(state);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> DeleteState(@PathVariable(value = "id") long id)
    {
        Optional<State> state = repository.findById(id);
        if(state.isPresent()){
            repository.delete(state.get());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
