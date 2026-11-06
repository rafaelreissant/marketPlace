package com.example.marketPlace.error.exceptions;

public class EmailInUse extends RuntimeException {
    public EmailInUse(String message) {
        super(message);
    }
}
