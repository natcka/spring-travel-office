package com.example.newtraveloffice.exceptions;

public class NoSuchTripException extends Exception {

    private final String MESSAGE = "W bazie nie ma wycieczki do ";

    public NoSuchTripException() {

    }

    @Override
    public String getMessage() {
        return MESSAGE;
    }
}
