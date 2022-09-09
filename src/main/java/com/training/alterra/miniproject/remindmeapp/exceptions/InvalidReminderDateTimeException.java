/**
 * @Author Nandang Sopyan
 * @ApplicationName remind.me app
 * @CreatedAt Sept 2022
 * @Description This is a REST API application as mini project task at alterra training academy program
 */
package com.training.alterra.miniproject.remindmeapp.exceptions;
public class InvalidReminderDateTimeException extends RuntimeException {
    public InvalidReminderDateTimeException() {
        super(String.format(
                "Invalid reminder_date_time. Date must be after current time"
        ));
    }
}