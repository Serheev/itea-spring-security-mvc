package com.serheev.exception;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(String e) {
        super(e);
    }

}
