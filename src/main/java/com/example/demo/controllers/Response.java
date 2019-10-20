package com.example.demo.controllers;

public class Response {

    private final Integer code;
    private final String message;
    private final Object result;

    Response(String message, Integer code, Object result) {
        this.code = code;
        this.message = message;
        this.result = result;
    }


    public Integer getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }

    public Object getResult() {
        return this.result;
    }
}