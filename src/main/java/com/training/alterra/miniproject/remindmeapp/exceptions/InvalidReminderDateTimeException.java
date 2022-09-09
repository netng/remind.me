package com.training.alterra.miniproject.remindmeapp.exceptions;

import java.time.ZonedDateTime;

public class InvalidReminderDateTimeException extends RuntimeException {
    public InvalidReminderDateTimeException() {
        super(String.format(
                "Invalid reminder_date_time. Date must be after current time"
        ));
    }
}
