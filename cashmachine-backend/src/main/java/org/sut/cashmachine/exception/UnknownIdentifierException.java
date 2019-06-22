package org.sut.cashmachine.exception;

public class UnknownIdentifierException extends RuntimeException {
    public UnknownIdentifierException(String message) {
        super(message);
    }
}
