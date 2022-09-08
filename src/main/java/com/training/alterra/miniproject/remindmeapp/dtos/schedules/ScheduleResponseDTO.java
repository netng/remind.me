package com.training.alterra.miniproject.remindmeapp.dtos.schedules;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(value = {"createdAt", "updatedAt"}, allowGetters = true)
@EqualsAndHashCode
public class ScheduleResponseDTO implements Serializable {
    private Long id;

    @JsonProperty("date_time")
    private LocalDateTime dateTime;
}
