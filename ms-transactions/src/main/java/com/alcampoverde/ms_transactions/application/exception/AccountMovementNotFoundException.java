package com.alcampoverde.ms_transactions.application.exception;

public class AccountMovementNotFoundException extends RuntimeException {
    public AccountMovementNotFoundException(String message) {
        super(message);
    }
}
