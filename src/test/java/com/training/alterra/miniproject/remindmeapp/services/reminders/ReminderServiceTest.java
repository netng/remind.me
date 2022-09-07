package com.training.alterra.miniproject.remindmeapp.services.reminders;

import com.training.alterra.miniproject.remindmeapp.dtos.reminders.ReminderRequestDTO;
import com.training.alterra.miniproject.remindmeapp.dtos.reminders.ReminderResponseDTO;
import com.training.alterra.miniproject.remindmeapp.dtos.users.UserRequestDTO;
import com.training.alterra.miniproject.remindmeapp.dtos.users.UserResponseDTO;
import com.training.alterra.miniproject.remindmeapp.entities.Reminder;
import com.training.alterra.miniproject.remindmeapp.entities.User;
import com.training.alterra.miniproject.remindmeapp.exceptions.ResourceNotFoundException;
import com.training.alterra.miniproject.remindmeapp.repositories.ReminderRepository;
import com.training.alterra.miniproject.remindmeapp.repositories.UserRepository;
import com.training.alterra.miniproject.remindmeapp.services.users.UserService;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willCallRealMethod;
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

        ReminderResponseDTO reminderCreated = reminderService.createNewReminder(setUser().getId(), setReminderDTO());

        assertThat(reminderCreated.getId())
                .isNotNull();

        assertThat(reminderCreated.getTitle())
                .isSameAs(reminder.getTitle());

        assertThat(reminderCreated.getDescription())
                .isSameAs(reminder.getDescription());

    }

    @Test
    public void listAllReminders_shouldReturnListOfReminders() {
        Reminder reminder = modelMapper.map(setReminderDTO(), Reminder.class);
        reminder.setId(1L);
        reminder.setUser(setUser());

        when(reminderRepository.save(any(Reminder.class)))
                .thenReturn(reminder);

        given(userRepository.findById(setUser().getId()))
                .willReturn(Optional.of(setUser()));

        List<ReminderResponseDTO> responseDTO = reminderService.listAllReminders(setUser().getId());
        assertNotNull(responseDTO);
    }

    @Test
    public void deleteReminder_whenReminderIdFound() {
        Reminder reminder = modelMapper.map(setReminderDTO(), Reminder.class);
        reminder.setId(1L);
        reminder.setUser(setUser());

        given(reminderRepository.findById(reminder.getId()))
                .willReturn(Optional.of(reminder));

        reminderService.deleteReminder(reminder.getId());
        verify(reminderRepository).deleteById(reminder.getId());

    }

    @Test(expected = ResourceNotFoundException.class)
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

        ReminderResponseDTO expected = reminderService.updateReminder(reminder.getId(), newReminderDTO);
        assertThat(expected.getTitle().equals(newReminderDTO.getTitle()));

    }

    @Test(expected = ResourceNotFoundException.class)
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

        ReminderResponseDTO expected = reminderService.showReminderDetail(reminder.getId());

        assertEquals(expected, currentReminder);
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
