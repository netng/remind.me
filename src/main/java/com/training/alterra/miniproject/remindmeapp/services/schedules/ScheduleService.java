package com.training.alterra.miniproject.remindmeapp.services.schedules;

import com.training.alterra.miniproject.remindmeapp.dtos.schedules.ScheduleRequestDTO;
import com.training.alterra.miniproject.remindmeapp.dtos.schedules.ScheduleResponseDTO;
import com.training.alterra.miniproject.remindmeapp.entities.Schedule;
import com.training.alterra.miniproject.remindmeapp.repositories.ReminderRepository;
import com.training.alterra.miniproject.remindmeapp.repositories.ScheduleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ScheduleService implements IScheduleService {

    @Autowired
    ScheduleRepository scheduleRepository;

    @Autowired
    ReminderRepository reminderRepository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public ScheduleResponseDTO createNewSchedule(Long reminderId, ScheduleRequestDTO requestDTO) {
        Optional<Schedule> schedule = reminderRepository.findById(reminderId)
                .map(reminder -> {
                    requestDTO.setReminder(reminder);
                    return scheduleRepository.save(convertToEntity(requestDTO));
                });
        return convertToDto(schedule.get());
    }

    private Schedule convertToEntity(ScheduleRequestDTO requestDTO) {
        return modelMapper.map(requestDTO, Schedule.class);
    }

    private ScheduleResponseDTO convertToDto(Schedule schedule) {
        return modelMapper.map(schedule, ScheduleResponseDTO.class);
    }
}
