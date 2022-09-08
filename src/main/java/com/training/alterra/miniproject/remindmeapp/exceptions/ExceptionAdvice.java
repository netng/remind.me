package com.training.alterra.miniproject.remindmeapp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ExceptionAdvice {

    @ResponseBody
    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String userNotFoundHandler(ResourceNotFoundException exception) {
        return exception.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(InvalidReminderDateTimeException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String invalidReminderDateTimeHandler(InvalidReminderDateTimeException exception) {
        return exception.getMessage();
    }
}