package com.example.marketPlace.error.exceptions;

public class OfferAlreadyMade extends RuntimeException {
    public OfferAlreadyMade(String message) {
        super(message);
    }
}
