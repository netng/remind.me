package com.training.alterra.miniproject.remindmeapp.dtos.reminders;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class ReminderResponseDTO implements Serializable {
    private Long id;
    private String title;
    private String description;
}
