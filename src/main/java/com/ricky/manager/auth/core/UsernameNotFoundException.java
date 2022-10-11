package com.ricky.manager.auth.core;

public class UsernameNotFoundException extends Exception {
    public UsernameNotFoundException() {
        super();
    }

    public UsernameNotFoundException(String message) {
        super(message);
    }
}
