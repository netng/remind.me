package com.training.alterra.miniproject.remindmeapp.exceptions;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(Long id) {
        super(String.format(
                "Could not find resource with id: %s",
                id
        ));
    }
}
