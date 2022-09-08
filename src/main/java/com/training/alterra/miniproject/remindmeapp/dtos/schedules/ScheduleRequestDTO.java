package com.training.alterra.miniproject.remindmeapp.dtos.schedules;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.training.alterra.miniproject.remindmeapp.entities.Reminder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleRequestDTO implements Serializable {
    @JsonProperty("date_time")
    private LocalDateTime dateTime;

    private Reminder reminder;
}
