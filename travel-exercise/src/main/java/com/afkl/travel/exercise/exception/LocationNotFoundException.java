package com.afkl.travel.exercise.exception;

public class LocationNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public LocationNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public LocationNotFoundException(String message) {
        super(message);
    }

    public LocationNotFoundException(Throwable message) {
        super(message);
    }

}
