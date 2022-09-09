package com.training.alterra.miniproject.remindmeapp.services.reminders;

import com.fasterxml.jackson.databind.ser.Serializers;
import com.training.alterra.miniproject.remindmeapp.dtos.BaseResponseDTO;
import com.training.alterra.miniproject.remindmeapp.dtos.PaginatedBaseResponseDTO;
import com.training.alterra.miniproject.remindmeapp.dtos.reminders.ReminderRequestDTO;
import com.training.alterra.miniproject.remindmeapp.dtos.reminders.ReminderResponseDTO;
import com.training.alterra.miniproject.remindmeapp.entities.Reminder;
import com.training.alterra.miniproject.remindmeapp.entities.User;
import com.training.alterra.miniproject.remindmeapp.exceptions.EntityNotFoundException;
import com.training.alterra.miniproject.remindmeapp.repositories.ReminderRepository;
import com.training.alterra.miniproject.remindmeapp.repositories.UserRepository;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ReminderServiceTest {

    @Mock
    ReminderRepository reminderRepository;

    @Mock
    UserRepository userRepository;

    @InjectMocks
    ReminderService reminderService = spy(new ReminderService());

    ModelMapper modelMapper = spy(new ModelMapper());

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    public void createNewReminder_shouldReturnNewCreatedReminder() {

        Reminder reminder = modelMapper.map(setReminderDTO(), Reminder.class);
        reminder.setId(1L);
        reminder.setUser(setUser());

        when(reminderRepository.save(any(Reminder.class)))
                .thenReturn(reminder);

        given(userRepository.findById(setUser().getId()))
                .willReturn(Optional.of(setUser()));

        BaseResponseDTO<String, String, ReminderResponseDTO> reminderCreated = reminderService.createNewReminder(setUser().getId(), setReminderDTO());

        assertThat(reminderCreated.getData().getId())
                .isNotNull();

        assertThat(reminderCreated.getData().getTitle())
                .isSameAs(reminder.getTitle());

        assertThat(reminderCreated.getData().getDescription())
                .isSameAs(reminder.getDescription());

    }

    @Test
    public void listAllReminders_shouldReturnListOfReminders() {
        Pageable pageable = null;
        Reminder reminder = modelMapper.map(setReminderDTO(), Reminder.class);
        reminder.setId(1L);
        reminder.setUser(setUser());

        when(reminderRepository.save(any(Reminder.class)))
                .thenReturn(reminder);

        given(userRepository.findById(setUser().getId()))
                .willReturn(Optional.of(setUser()));

        Page<Reminder> expected = reminderRepository.findByUserId(setUser().getId(), pageable);

        PaginatedBaseResponseDTO<String, String, List<ReminderResponseDTO>> actual = reminderService.listAllReminders(setUser().getId(), pageable);
        assertThat(expected).usingRecursiveComparison().isEqualTo(actual);
        System.out.println(expected);
        System.out.println(actual);
    }

    @Test
    public void deleteReminder_whenReminderIdFound() {
        Reminder reminder = modelMapper.map(setReminderDTO(), Reminder.class);
        reminder.setId(1L);
        reminder.setUser(setUser());

        given(reminderRepository.findById(reminder.getId()))
                .willReturn(Optional.of(reminder));

        BaseResponseDTO<String, String, ReminderResponseDTO> expected = new BaseResponseDTO<>(
                "OK", "Successfully deleting data", null);

        BaseResponseDTO<String, String, ReminderResponseDTO> response = reminderService.deleteReminder(reminder.getId());

        assertThat(response).usingRecursiveComparison().isEqualTo(expected);
    }

    @Test(expected = EntityNotFoundException.class)
    public void shouldThrowException_whenReminderIdNotFound_onDeleteReminder() {
        given(reminderRepository.findById(anyLong()))
                .willReturn(Optional.ofNullable(null));

        reminderService.deleteReminder(setUser().getId());
    }

    @Test
    public void updateUser_whenGivenIdFound() {
        Reminder reminder = modelMapper.map(setReminderDTO(), Reminder.class);
        reminder.setId(1L);
        reminder.setUser(setUser());

        ReminderRequestDTO newReminderDTO = new ReminderRequestDTO();
        newReminderDTO.setTitle("latihan coding");

        given(reminderRepository.findById(reminder.getId()))
                .willReturn(Optional.of(reminder));

        when(reminderRepository.save(any(Reminder.class)))
                .thenReturn(reminder);

        BaseResponseDTO<String, String, ReminderResponseDTO> expected = reminderService.updateReminder(reminder.getId(), newReminderDTO);
        assertThat(expected.getData().getTitle().equals(newReminderDTO.getTitle()));

    }

    @Test(expected = EntityNotFoundException.class)
    public void shouldThrowException_whenUserNotFound_onUpdateUser() {
        Reminder reminder = modelMapper.map(setReminderDTO(), Reminder.class);
        reminder.setUser(setUser());

        ReminderRequestDTO newReminderDTO = new ReminderRequestDTO();
        newReminderDTO.setTitle("bikin kue");

        given(reminderRepository.findById(anyLong()))
                .willReturn(Optional.ofNullable(null));

        reminderService.updateReminder(reminder.getId(), newReminderDTO);
    }

    @Test
    public void showUserDetail_whenUserFound() {
        Reminder reminder = modelMapper.map(setReminderDTO(), Reminder.class);
        reminder.setUser(setUser());

        given(reminderRepository.findById(reminder.getId()))
                .willReturn(Optional.of(reminder));

        ReminderResponseDTO currentReminder = modelMapper.map(reminder, ReminderResponseDTO.class);

        BaseResponseDTO<String, String, ReminderResponseDTO> expected = new BaseResponseDTO<>(
                "OK", "Success", currentReminder
        );

        BaseResponseDTO<String, String, ReminderResponseDTO> actual = reminderService.showReminderDetail(reminder.getId());

        assertThat(expected).usingRecursiveComparison().isEqualTo(actual);
    }


    /**
    @Test(expected = UserNotFoundException.class)
    public void shouldThrowException_whenUserNotFound_onShowUserDetail() {
        User user = modelMapper.map(newUserDTO(), User.class);
        System.out.println(user);
        userService.showUserDetail(user.getId());
    }

    private UserRequestDTO newUserDTO() {
        UserRequestDTO userDTO = new UserRequestDTO();
        userDTO.setFullName("nandang super papa");
        userDTO.setEmail("net.nandang@gmail.com");
        userDTO.setPassword("password");

        return userDTO;
    }
    */

    private User setUser() {
        User user = new User();
        user.setId(1L);
        user.setFullName("nandang");
        user.setEmail("net.nandang@gmail.com");
        user.setPassword("password");

        return user;
    }

    private ReminderRequestDTO setReminderDTO() {
        ReminderRequestDTO requestDTO = new ReminderRequestDTO();
        requestDTO.setTitle("Angkat jemuran");
        requestDTO.setDescription("angkat jemuran jangan sampai ke hujanan");

        return requestDTO;
    }
}
