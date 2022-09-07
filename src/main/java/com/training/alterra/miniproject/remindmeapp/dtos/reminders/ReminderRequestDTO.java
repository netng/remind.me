package com.training.alterra.miniproject.remindmeapp.dtos.reminders;

import com.training.alterra.miniproject.remindmeapp.entities.User;
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

    private User user;
}
