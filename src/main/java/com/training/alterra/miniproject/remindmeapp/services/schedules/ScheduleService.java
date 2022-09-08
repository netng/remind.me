package com.training.alterra.miniproject.remindmeapp.services.schedules;

import com.training.alterra.miniproject.remindmeapp.dtos.schedules.ScheduleRequestDTO;
import com.training.alterra.miniproject.remindmeapp.dtos.schedules.ScheduleResponseDTO;
import org.springframework.stereotype.Service;

@Service
public class ScheduleService implements IScheduleService {
    @Override
    public ScheduleResponseDTO createNewSchedule(Long reminderId, ScheduleRequestDTO requestDTO) {
        return null;
    }
}
