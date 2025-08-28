package com.alcampoverde.ms_transactions.infraestructure.out.messaging.exception;

public class KafkaProducerRecivedException extends RuntimeException {

    public KafkaProducerRecivedException(String message) {
        super(message);
    }

    public KafkaProducerRecivedException(String message, Throwable cause) {
        super(message, cause);
    }
}
