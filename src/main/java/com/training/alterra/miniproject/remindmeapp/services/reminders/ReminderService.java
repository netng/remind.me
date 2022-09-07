package com.training.alterra.miniproject.remindmeapp.services.reminders;

import com.training.alterra.miniproject.remindmeapp.dtos.reminders.ReminderRequestDTO;
import com.training.alterra.miniproject.remindmeapp.dtos.reminders.ReminderResponseDTO;
import com.training.alterra.miniproject.remindmeapp.entities.Reminder;
import com.training.alterra.miniproject.remindmeapp.entities.User;
import com.training.alterra.miniproject.remindmeapp.exceptions.ResourceNotFoundException;
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
    public ReminderResponseDTO createNewReminder(Long userId, ReminderRequestDTO requestDTO) {
        Optional<Reminder> reminder = userRepository.findById(userId)
                .map(user -> {
                    requestDTO.setUser(user);
                    return reminderRepository.save(convertToEntity(requestDTO));
                });

        return convertToDto(reminder.get());
    }

    @Override
    public List<ReminderResponseDTO> listAllReminders(Long userId) {
        userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException(userId));

        List<Reminder> reminders = reminderRepository.findByUserId(userId);

        if (!reminders.isEmpty()) {
            return reminders.stream()
                    .map(reminder -> convertToDto(reminder))
                    .collect(Collectors.toList());
        }

        return Collections.emptyList();
    }

    @Override
    public void deleteReminder(Long reminderId) {
        reminderRepository.findById(reminderId)
                .orElseThrow(() -> new ResourceNotFoundException(reminderId));

        reminderRepository.deleteById(reminderId);
    }

    @Override
    public ReminderResponseDTO updateReminder(Long reminderId, ReminderRequestDTO requestDTO) {
       Reminder reminder = reminderRepository.findById(reminderId)
                .orElseThrow(() -> new ResourceNotFoundException(reminderId));

        reminder.setTitle(requestDTO.getTitle());
        reminder.setDescription(requestDTO.getDescription());

        return convertToDto(reminderRepository.save(reminder));
    }

    @Override
    public ReminderResponseDTO showReminderDetail(Long reminderId) {
        reminderRepository.findById(reminderId)
                .orElseThrow(() -> new ResourceNotFoundException(reminderId));

        Optional<Reminder> reminder = reminderRepository.findById(reminderId);
        return convertToDto(reminder.get());
    }


    private Reminder convertToEntity(ReminderRequestDTO requestDTO) {
        return modelMapper.map(requestDTO, Reminder.class);
    }

    private ReminderResponseDTO convertToDto(Reminder reminder) {
        return modelMapper.map(reminder, ReminderResponseDTO.class);
    }
}
