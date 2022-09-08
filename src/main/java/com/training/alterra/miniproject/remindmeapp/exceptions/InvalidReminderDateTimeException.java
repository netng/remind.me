package com.training.alterra.miniproject.remindmeapp.exceptions;

import java.time.ZonedDateTime;

public class InvalidReminderDateTimeException extends RuntimeException {
    public InvalidReminderDateTimeException(ZonedDateTime zonedDateTime) {
        super(String.format(
                "Invalid date time. Date must be after current time -- %s",
                zonedDateTime
        ));
    }
}
