package com.example.demo.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CounterExistValidation.class)
public @interface CounterExist {
    String message() default "Counter not found";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
