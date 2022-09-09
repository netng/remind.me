package com.training.alterra.miniproject.remindmeapp.services.schedules;


import com.training.alterra.miniproject.remindmeapp.dtos.BaseResponseDTO;
import com.training.alterra.miniproject.remindmeapp.dtos.PaginatedBaseResponseDTO;
import com.training.alterra.miniproject.remindmeapp.dtos.schedules.ScheduleRequestDTO;
import com.training.alterra.miniproject.remindmeapp.dtos.schedules.ScheduleResponseDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IScheduleService {
    BaseResponseDTO<String, String, ScheduleResponseDTO> createNewSchedule(Long reminderId, ScheduleRequestDTO requestDTO);

    PaginatedBaseResponseDTO<String, String, List<ScheduleResponseDTO>> showAllSchedules(Long reminderId, Pageable pageable);
}
