package com.example.demo.repository;

import javax.validation.ValidationException;

public class AlreadyExistException extends ValidationException {
    public AlreadyExistException(String message) {
        super(message);
    }

    public AlreadyExistException() {
        super();
    }

    public AlreadyExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public AlreadyExistException(Throwable cause) {
        super(cause);
    }

}
