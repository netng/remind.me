package com.training.alterra.miniproject.remindmeapp.dtos.reminders;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReminderRequestDTO implements Serializable {
    private String name;
    private String description;
}
