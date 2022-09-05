package com.training.alterra.miniproject.remindmeapp.exceptions;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(Long id) {
        super(String.format(
                "Could not find user with id: %s",
                id
        ));
    }
}
