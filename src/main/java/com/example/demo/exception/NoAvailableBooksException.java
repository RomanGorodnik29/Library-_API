package com.example.demo.exception;

public class NoAvailableBooksException extends RuntimeException {

    public NoAvailableBooksException(String message) {
        super(message);
    }
}