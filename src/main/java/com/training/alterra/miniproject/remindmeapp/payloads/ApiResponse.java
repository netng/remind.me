package com.training.alterra.miniproject.remindmeapp.payloads;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiResponse<T> {
    private String status;
    private T data;

    public ApiResponse(String status, T data) {
        this.status = status;
        this.data = data;
    }
}
