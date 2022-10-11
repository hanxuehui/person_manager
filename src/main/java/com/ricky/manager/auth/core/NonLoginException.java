package com.ricky.manager.auth.core;

public class NonLoginException extends Exception {
    public NonLoginException() {
        super();
    }

    public NonLoginException(String message) {
        super(message);
    }
}
