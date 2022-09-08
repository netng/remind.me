package com.training.alterra.miniproject.remindmeapp.dtos.schedules;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.training.alterra.miniproject.remindmeapp.entities.Reminder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleRequestDTO implements Serializable {
    @JsonProperty("date_time")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date reminderDate;

    private Reminder reminder;
}
