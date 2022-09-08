package com.training.alterra.miniproject.remindmeapp.controllers;

import com.training.alterra.miniproject.remindmeapp.dtos.schedules.ScheduleRequestDTO;
import com.training.alterra.miniproject.remindmeapp.dtos.schedules.ScheduleResponseDTO;
import com.training.alterra.miniproject.remindmeapp.services.schedules.IScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class SchedulesController {

    @Autowired
    IScheduleService scheduleService;

    @PostMapping("/reminders/{reminderId}/schedules")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ScheduleResponseDTO> createNewSchedule(@PathVariable Long reminderId, @RequestBody ScheduleRequestDTO requestDTO) {
        ScheduleResponseDTO schedule = scheduleService.createNewSchedule(reminderId, requestDTO);
        return ResponseEntity.ok().body(schedule);
    }

    @GetMapping("/reminders/{reminderId}/schedules")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<ScheduleResponseDTO>> listAllSchedules(@PathVariable Long reminderId) {
        List<ScheduleResponseDTO> schedules = scheduleService.showAllSchedules(reminderId);
        return ResponseEntity.ok().body(schedules);

    }
}
