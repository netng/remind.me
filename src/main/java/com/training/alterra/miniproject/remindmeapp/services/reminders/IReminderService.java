package com.training.alterra.miniproject.remindmeapp.services.reminders;

import com.training.alterra.miniproject.remindmeapp.dtos.reminders.ReminderRequestDTO;
import com.training.alterra.miniproject.remindmeapp.dtos.reminders.ReminderResponseDTO;
import com.training.alterra.miniproject.remindmeapp.entities.User;

import java.util.List;

public interface IReminderService {
    ReminderResponseDTO createNewReminder(Long userId, ReminderRequestDTO requestDTO);

    List<ReminderResponseDTO> listAllReminders(Long userId);

    void deleteReminder(Long reminderId);

    ReminderResponseDTO updateReminder(Long reminderId, ReminderRequestDTO requestDTO);

    ReminderResponseDTO showReminderDetail(Long reminderId);
}
