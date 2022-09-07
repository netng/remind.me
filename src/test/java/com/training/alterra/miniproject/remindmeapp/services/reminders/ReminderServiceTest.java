package com.training.alterra.miniproject.remindmeapp.services.reminders;

import com.training.alterra.miniproject.remindmeapp.dtos.reminders.ReminderRequestDTO;
import com.training.alterra.miniproject.remindmeapp.dtos.reminders.ReminderResponseDTO;
import com.training.alterra.miniproject.remindmeapp.dtos.users.UserRequestDTO;
import com.training.alterra.miniproject.remindmeapp.dtos.users.UserResponseDTO;
import com.training.alterra.miniproject.remindmeapp.entities.Reminder;
import com.training.alterra.miniproject.remindmeapp.entities.User;
import com.training.alterra.miniproject.remindmeapp.exceptions.UserNotFoundException;
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
        User user = new User();
        user.setId(1L);
        user.setFullName("nandang");
        user.setEmail("net.nandang@gmail.com");
        user.setPassword("password");

        ReminderRequestDTO requestDTO = new ReminderRequestDTO();
        requestDTO.setName("Angkat jemuran");
        requestDTO.setDescription("angkat jemuran jangan sampai ke hujanan");

        Reminder reminder = modelMapper.map(requestDTO, Reminder.class);
        reminder.setId(1L);
        reminder.setUser(user);

        when(reminderRepository.save(any(Reminder.class)))
                .thenReturn(reminder);

        given(userRepository.findById(user.getId()))
                .willReturn(Optional.of(user));

        ReminderResponseDTO reminderCreated = reminderService.createNewReminder(user.getId(), requestDTO);

        assertThat(reminderCreated.getId())
                .isNotNull();

        assertThat(reminderCreated.getName())
                .isSameAs(reminder.getName());

        assertThat(reminderCreated.getDescription())
                .isSameAs(reminder.getDescription());

    }

    @Test
    public void listAllReminders_shouldReturnListOfReminders() {
        User user = new User();
        user.setId(1L);
        user.setFullName("nandang");
        user.setEmail("net.nandang@gmail.com");
        user.setPassword("password");

        ReminderRequestDTO requestDTO = new ReminderRequestDTO();
        requestDTO.setName("Angkat jemuran");
        requestDTO.setDescription("angkat jemuran jangan sampai ke hujanan");

        Reminder reminder = modelMapper.map(requestDTO, Reminder.class);
        reminder.setId(1L);
        reminder.setUser(user);

        when(reminderRepository.save(any(Reminder.class)))
                .thenReturn(reminder);

        given(userRepository.findById(user.getId()))
                .willReturn(Optional.of(user));

        List<ReminderResponseDTO> responseDTO = reminderService.listAllReminders(user.getId());
        assertNotNull(responseDTO);
    }

    /**
    @Test
    public void deleteUser_ifGivenIdFound() {
        User user = new User();
        user.setFullName("nandang");
        user.setPassword("password");
        user.setEmail("net.nandang@gmail.com");
        user.setId(1L);

        when(userRepository.findById(user.getId()))
                .thenReturn(Optional.of(user));

        userService.deleteUser(user.getId());
        verify(userRepository).deleteById(user.getId());
    }

    @Test(expected = RuntimeException.class)
    public void shouldThrowException_whenUserNotFound_onDeleteUser() {
        User user = new User();
        user.setId(69L);
        user.setFullName("nandang super papa");
        user.setEmail("net.nandang@gmail.com");
        user.setPassword("password");

        given(userRepository.findById(anyLong()))
                .willReturn(Optional.ofNullable(null));

        userService.deleteUser(user.getId());
    }

    @Test
    public void updateUser_whenGivenIdFound() {
        User user = modelMapper.map(newUserDTO(), User.class);
        user.setId(1L);

        UserRequestDTO newUserDTO = new UserRequestDTO();
        newUserDTO.setFullName("nandang ganteng");

        given(userRepository.findById(user.getId()))
                .willReturn(Optional.of(user));
        when(userRepository.save(any(User.class)))
                .thenReturn(user);

        userService.updateUser(user.getId(), newUserDTO);

    }

    @Test(expected = RuntimeException.class)
    public void shouldThrowException_whenUserNotFound_onUpdateUser() {
        User user = modelMapper.map(newUserDTO(), User.class);
        user.setId(1L);

        UserRequestDTO newUserDTO = new UserRequestDTO();
        newUserDTO.setFullName("nandang papa");

        given(userRepository.findById(anyLong()))
                .willReturn(Optional.ofNullable(null));

        userService.updateUser(user.getId(), newUserDTO);
    }

    @Test
    public void showUserDetail_whenUserFound() {
        User user = modelMapper.map(newUserDTO(), User.class);
        user.setId(1L);
        userRepository.save(user);

        given(userRepository.findById(user.getId()))
                .willReturn(Optional.of(user));

        UserResponseDTO currentUser = modelMapper.map(user, UserResponseDTO.class);

        UserResponseDTO expected = userService.showUserDetail(user.getId());

        assertEquals(expected, currentUser);
    }

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
}
