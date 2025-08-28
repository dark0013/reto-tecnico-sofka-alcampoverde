package com.alcampoverde.ms_transactions.application.exception;

public class InvalidMovementTypeException extends RuntimeException {
    public InvalidMovementTypeException(String message) {
        super(message);
    }
}
