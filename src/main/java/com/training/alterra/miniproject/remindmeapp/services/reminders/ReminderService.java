package com.training.alterra.miniproject.remindmeapp.services.reminders;

import com.training.alterra.miniproject.remindmeapp.dtos.reminders.ReminderRequestDTO;
import com.training.alterra.miniproject.remindmeapp.dtos.reminders.ReminderResponseDTO;
import com.training.alterra.miniproject.remindmeapp.entities.Reminder;
import com.training.alterra.miniproject.remindmeapp.entities.User;
import com.training.alterra.miniproject.remindmeapp.repositories.ReminderRepository;
import com.training.alterra.miniproject.remindmeapp.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

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


    private Reminder convertToEntity(ReminderRequestDTO requestDTO) {
        return modelMapper.map(requestDTO, Reminder.class);
    }

    private ReminderResponseDTO convertToDto(Reminder reminder) {
        return modelMapper.map(reminder, ReminderResponseDTO.class);
    }
}
