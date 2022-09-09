package com.training.alterra.miniproject.remindmeapp.controllers;

import com.fasterxml.jackson.databind.ser.Serializers;
import com.training.alterra.miniproject.remindmeapp.dtos.BaseResponseDTO;
import com.training.alterra.miniproject.remindmeapp.dtos.reminders.ReminderRequestDTO;
import com.training.alterra.miniproject.remindmeapp.dtos.reminders.ReminderResponseDTO;
import com.training.alterra.miniproject.remindmeapp.services.reminders.IReminderService;
import com.training.alterra.miniproject.remindmeapp.services.reminders.ReminderService;
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
    public ResponseEntity<BaseResponseDTO<String, String, ReminderResponseDTO>> createNewReminder(@PathVariable Long userId, @RequestBody ReminderRequestDTO requestDTO) {
        BaseResponseDTO<String, String, ReminderResponseDTO> responseDTO = reminderService.createNewReminder(userId, requestDTO);
        return ResponseEntity.ok().body(responseDTO);
    }

    @GetMapping("/users/{userId}/reminders")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<BaseResponseDTO<String, String, List<ReminderResponseDTO>>> listAllReminders(@PathVariable Long userId) {
        BaseResponseDTO<String, String, List<ReminderResponseDTO>> reminderResponseDTOS = reminderService.listAllReminders(userId);
        return ResponseEntity.ok().body(reminderResponseDTOS);
    }

    @DeleteMapping("/reminders/{reminderId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<BaseResponseDTO<String, String, ReminderResponseDTO>> deleteReminder(@PathVariable Long reminderId) {
        BaseResponseDTO<String, String, ReminderResponseDTO> response = reminderService.deleteReminder(reminderId);
        return ResponseEntity.ok().body(response);
    }

    @PutMapping("/reminders/{reminderId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<BaseResponseDTO<String, String, ReminderResponseDTO>> updateReminder(@PathVariable Long reminderId, @RequestBody ReminderRequestDTO requestDTO) {
        return ResponseEntity.ok().body(reminderService.updateReminder(reminderId, requestDTO));
    }

    @GetMapping("/reminders/{reminderId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<BaseResponseDTO<String, String, ReminderResponseDTO>> showReminderDetail(@PathVariable Long reminderId) {
        return ResponseEntity.ok().body(reminderService.showReminderDetail(reminderId));
    }
}
