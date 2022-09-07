package com.training.alterra.miniproject.remindmeapp.controllers;

import com.training.alterra.miniproject.remindmeapp.dtos.reminders.ReminderRequestDTO;
import com.training.alterra.miniproject.remindmeapp.dtos.reminders.ReminderResponseDTO;
import com.training.alterra.miniproject.remindmeapp.services.reminders.IReminderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class RemindersController {

    @Autowired
    IReminderService reminderService;

    @PostMapping("/users/{userId}/reminders")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ReminderResponseDTO> createNewReminder(@PathVariable Long userId, @RequestBody ReminderRequestDTO requestDTO) {
        ReminderResponseDTO responseDTO = reminderService.createNewReminder(userId, requestDTO);
        return ResponseEntity.ok().body(responseDTO);
    }

    @GetMapping("/users/{userId}/reminders")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<ReminderResponseDTO>> listAllReminders(@PathVariable Long userId) {
        List<ReminderResponseDTO> reminderResponseDTOS = reminderService.listAllReminders(userId);
        return ResponseEntity.ok().body(reminderResponseDTOS);
    }
}
