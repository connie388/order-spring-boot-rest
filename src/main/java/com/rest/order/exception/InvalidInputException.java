package com.rest.order.exception;

public class InvalidInputException extends RuntimeException {

    public InvalidInputException() {
        super("Invalid Input");
    }

    public InvalidInputException(String message) {
        super(message);
    }

    public InvalidInputException(String message, Throwable cause) {
        super(message, cause);
    }
}