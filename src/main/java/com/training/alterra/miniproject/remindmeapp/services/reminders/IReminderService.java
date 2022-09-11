/**
 * @Author Nandang Sopyan
 * @ApplicationName remind.me app
 * @CreatedAt Sept 2022
 * @Description This is a REST API application as mini project task at alterra training academy program
 */
package com.training.alterra.miniproject.remindmeapp.services.reminders;

import com.training.alterra.miniproject.remindmeapp.dtos.BaseResponseDTO;
import com.training.alterra.miniproject.remindmeapp.dtos.PaginatedBaseResponseDTO;
import com.training.alterra.miniproject.remindmeapp.dtos.reminders.ReminderRequestDTO;
import com.training.alterra.miniproject.remindmeapp.dtos.reminders.ReminderResponseDTO;
import com.training.alterra.miniproject.remindmeapp.entities.Reminder;
import com.training.alterra.miniproject.remindmeapp.entities.User;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.IOException;
import java.util.List;

public interface IReminderService {
    BaseResponseDTO<String, String, ReminderResponseDTO> createNewReminder(
            Long userId, ReminderRequestDTO requestDTO);

    PaginatedBaseResponseDTO<String, String, List<ReminderResponseDTO>> listAllReminders(
            Long userId, Pageable pageable);

    BaseResponseDTO<String, String, ReminderResponseDTO> deleteReminder(Long reminderId);

    BaseResponseDTO<String, String, ReminderResponseDTO> updateReminder(
            Long reminderId, ReminderRequestDTO requestDTO);

    BaseResponseDTO<String, String, ReminderResponseDTO> showReminderDetail(Long reminderId);

    Page<Reminder> exportToExcel(Long userId, Pageable pageable);
}