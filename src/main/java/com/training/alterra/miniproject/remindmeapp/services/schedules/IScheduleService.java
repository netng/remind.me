package com.training.alterra.miniproject.remindmeapp.services.schedules;


import com.training.alterra.miniproject.remindmeapp.dtos.BaseResponseDTO;
import com.training.alterra.miniproject.remindmeapp.dtos.schedules.ScheduleRequestDTO;
import com.training.alterra.miniproject.remindmeapp.dtos.schedules.ScheduleResponseDTO;

import java.util.List;

public interface IScheduleService {
    BaseResponseDTO<String, String, ScheduleResponseDTO> createNewSchedule(Long reminderId, ScheduleRequestDTO requestDTO);

    BaseResponseDTO<String, String, List<ScheduleResponseDTO>> showAllSchedules(Long reminderId);
}
