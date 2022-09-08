package com.training.alterra.miniproject.remindmeapp.services.schedules;


import com.training.alterra.miniproject.remindmeapp.dtos.schedules.ScheduleRequestDTO;
import com.training.alterra.miniproject.remindmeapp.dtos.schedules.ScheduleResponseDTO;

import java.util.List;

public interface IScheduleService {
    ScheduleResponseDTO createNewSchedule(Long reminderId, ScheduleRequestDTO requestDTO);

    List<ScheduleResponseDTO> showAllSchedules(Long reminderId);
}
