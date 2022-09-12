package com.training.alterra.miniproject.remindmeapp.payloads;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DataResponse {
    private String message;

    public DataResponse(String message) {
        this.message = message;
    }
}
