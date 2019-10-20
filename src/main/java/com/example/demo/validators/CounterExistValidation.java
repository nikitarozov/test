package com.example.demo.validators;

import com.example.demo.repository.CounterRepository;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CounterExistValidation implements ConstraintValidator<CounterExist, String> {
    private final CounterRepository counterRepository;

    public CounterExistValidation(CounterRepository counterRepository) {
        this.counterRepository = counterRepository;
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return this.counterRepository.isExist(value);
    }
}



