package com.example.demo.controllers;

import com.example.demo.repository.CounterRepository;
import com.example.demo.validators.CounterExist;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("counter")
@Validated
public class AppController {

    private final CounterRepository counterRepository;

    public AppController(CounterRepository counterRepository) {
        this.counterRepository = counterRepository;
    }

    @GetMapping(value = "{name}")
    public Response getCounterCount(@PathVariable @CounterExist String name) {
        return new Response(null, HttpStatus.OK.value(), counterRepository.get(name));
    }

    @PostMapping(value = "{name}")
    public Response createCounter(@PathVariable String name) {
        counterRepository.add(name);
        return new Response(null, HttpStatus.OK.value(), null);
    }

    @PutMapping(value = "{name}")
    public Response incrementCounter(@PathVariable @CounterExist String name) {
        counterRepository.increment(name);
        return new Response(null, HttpStatus.OK.value(), null);
    }

    @DeleteMapping(value = "{name}")
    public Response deleteCounter(@PathVariable @CounterExist String name) {
        counterRepository.delete(name);
        return new Response(null, HttpStatus.OK.value(), null);
    }

    @GetMapping(value = "all/sum")
    public Response getSum() {
        return new Response(null, HttpStatus.OK.value(), counterRepository.countAll());
    }


    @GetMapping(value = "all/list")
    public Set<String> getCounterList() {
        return counterRepository.getAll();
    }

}
