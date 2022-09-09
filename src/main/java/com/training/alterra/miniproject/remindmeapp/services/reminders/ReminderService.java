package com.training.alterra.miniproject.remindmeapp.services.reminders;

import com.training.alterra.miniproject.remindmeapp.dtos.BaseResponseDTO;
import com.training.alterra.miniproject.remindmeapp.dtos.reminders.ReminderRequestDTO;
import com.training.alterra.miniproject.remindmeapp.dtos.reminders.ReminderResponseDTO;
import com.training.alterra.miniproject.remindmeapp.entities.Reminder;
import com.training.alterra.miniproject.remindmeapp.exceptions.EntityNotFoundException;
import com.training.alterra.miniproject.remindmeapp.repositories.ReminderRepository;
import com.training.alterra.miniproject.remindmeapp.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReminderService implements IReminderService {

    @Autowired
    ReminderRepository reminderRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ModelMapper modelMapper;
    @Override
    public BaseResponseDTO<String, String, ReminderResponseDTO> createNewReminder(Long userId, ReminderRequestDTO requestDTO) {
        Optional<Reminder> reminder = userRepository.findById(userId)
                .map(user -> {
                    requestDTO.setUser(user);
                    return reminderRepository.save(convertToEntity(requestDTO));
                });

        return new BaseResponseDTO<>("OK", "Successfully creating data", convertToDto(reminder.get()));
    }

    @Override
    public BaseResponseDTO<String, String, List<ReminderResponseDTO>> listAllReminders(Long userId) {
        userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException(userId));

        List<Reminder> reminders = reminderRepository.findByUserId(userId);

        if (!reminders.isEmpty()) {
            List<ReminderResponseDTO> responseDTO = reminders.stream()
                    .map(reminder -> convertToDto(reminder))
                    .collect(Collectors.toList());

            return new BaseResponseDTO<>("OK", "Successfully retrieving data", responseDTO);
        }

        return new BaseResponseDTO<>("OK", "Data is empty", Collections.emptyList());
    }

    @Override
    public BaseResponseDTO<String, String, ReminderResponseDTO> deleteReminder(Long reminderId) {
        reminderRepository.findById(reminderId)
                .orElseThrow(() -> new EntityNotFoundException(reminderId));

        reminderRepository.deleteById(reminderId);
        return new BaseResponseDTO<>("OK", "Successfully deleting data", null);
    }

    @Override
    public BaseResponseDTO<String, String, ReminderResponseDTO> updateReminder(Long reminderId, ReminderRequestDTO requestDTO) {
       Reminder reminder = reminderRepository.findById(reminderId)
                .orElseThrow(() -> new EntityNotFoundException(reminderId));

        reminder.setTitle(requestDTO.getTitle());
        reminder.setDescription(requestDTO.getDescription());

        ReminderResponseDTO response = convertToDto(reminderRepository.save(reminder));
        return new BaseResponseDTO<>("OK", "Successfully updating data", response);
    }

    @Override
    public BaseResponseDTO<String, String, ReminderResponseDTO> showReminderDetail(Long reminderId) {
        reminderRepository.findById(reminderId)
                .orElseThrow(() -> new EntityNotFoundException(reminderId));

        Optional<Reminder> reminder = reminderRepository.findById(reminderId);
        return new BaseResponseDTO<>("OK", "Success", convertToDto(reminder.get()));
    }


    private Reminder convertToEntity(ReminderRequestDTO requestDTO) {
        return modelMapper.map(requestDTO, Reminder.class);
    }

    private ReminderResponseDTO convertToDto(Reminder reminder) {
        return modelMapper.map(reminder, ReminderResponseDTO.class);
    }
}
