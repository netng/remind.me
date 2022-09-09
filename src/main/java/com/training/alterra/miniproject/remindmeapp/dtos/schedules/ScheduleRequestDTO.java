/**
 * @Author Nandang Sopyan
 * @ApplicationName remind.me app
 * @CreatedAt Sept 2022
 * @Description This is a REST API application as mini project task at alterra training academy program
 */
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
    @JsonProperty("reminder_date_time")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime reminderDateTime;

    private Reminder reminder;
}