package com.training.alterra.miniproject.remindmeapp.services.reminders;

import com.training.alterra.miniproject.remindmeapp.dtos.BaseResponseDTO;
import com.training.alterra.miniproject.remindmeapp.dtos.reminders.ReminderRequestDTO;
import com.training.alterra.miniproject.remindmeapp.dtos.reminders.ReminderResponseDTO;
import com.training.alterra.miniproject.remindmeapp.entities.User;

import java.util.List;

public interface IReminderService {
    BaseResponseDTO<String, String, ReminderResponseDTO> createNewReminder(Long userId, ReminderRequestDTO requestDTO);

    BaseResponseDTO<String, String, List<ReminderResponseDTO>> listAllReminders(Long userId);

    BaseResponseDTO<String, String, ReminderResponseDTO> deleteReminder(Long reminderId);

    BaseResponseDTO<String, String, ReminderResponseDTO> updateReminder(Long reminderId, ReminderRequestDTO requestDTO);

    BaseResponseDTO<String, String, ReminderResponseDTO> showReminderDetail(Long reminderId);
}
