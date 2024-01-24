package com.example.productlistlayout.exception;

public class NonUniqueLoginException extends Exception{

    public NonUniqueLoginException() {
        super();
    }

    public NonUniqueLoginException(String message) {
        super(message);
    }
}