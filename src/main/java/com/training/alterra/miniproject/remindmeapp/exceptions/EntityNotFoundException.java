package com.training.alterra.miniproject.remindmeapp.exceptions;

public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(Long id) {
        super(String.format(
                "Could not find resource with id: %s",
                id
        ));
    }
}