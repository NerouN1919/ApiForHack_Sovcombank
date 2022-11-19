package com.tourney.tourney_api.Errors;

public class Failed extends RuntimeException{

    public Failed(String message) {
        super(message);
    }
}
