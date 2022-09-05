package com.training.alterra.miniproject.remindmeapp.services.users;

import com.training.alterra.miniproject.remindmeapp.dtos.users.UserRequestDTO;
import com.training.alterra.miniproject.remindmeapp.dtos.users.UserResponseDTO;
import com.training.alterra.miniproject.remindmeapp.entities.User;
import com.training.alterra.miniproject.remindmeapp.exceptions.UserNotFoundException;
import com.training.alterra.miniproject.remindmeapp.repositories.UserRepository;
import com.training.alterra.miniproject.remindmeapp.services.users.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @Mock
    UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    ModelMapper modelMapper = spy(new ModelMapper());

    @Test
    public void createNewUser_shouldReturnNewCreatedUser() {
        UserRequestDTO requestDTO = new UserRequestDTO();
        requestDTO.setFullName("nandang");
        requestDTO.setEmail("net.nandang@gmail.com");
        requestDTO.setPassword("password");

        User user = modelMapper.map(requestDTO, User.class);
        user.setId(1L);

        when(userRepository.save(any(User.class)))
                .thenReturn(user);

        UserResponseDTO userCreated = userService.createNewUser(requestDTO);

        assertThat(userCreated.getId())
                .isNotNull();

        assertThat(userCreated.getFullName())
                .isSameAs(user.getFullName());

        assertThat(userCreated.getEmail())
                .isSameAs(user.getEmail());
    }

    @Test
    public void listAllUsers_shouldReturnListOfUsers() {
        List<User> users = new ArrayList<>();
        users.add(new User());

        given(userRepository.findAll()).willReturn(users);

        List<User> expected = userService.listAllUsers();

        assertEquals(expected, users);
        verify(userRepository).findAll();
    }

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
        User user = new User();
        user.setId(99L);
        user.setFullName("nandang super papa");
        user.setEmail("net.nandang@gmail.com");
        user.setPassword("password");

        User newUser = new User();
        user.setFullName("nandang papa engineer");

        given(userRepository.findById(user.getId()))
                .willReturn(Optional.of(user));
        userService.updateUser(user.getId(), newUser);
    }

    @Test(expected = RuntimeException.class)
    public void shouldThrowException_whenUserNotFound_onUpdateUser() {
        User user = new User();
        user.setId(26L);
        user.setFullName("nandang");
        user.setEmail("net.nandang@gmail.com");
        user.setPassword("password");

        User newUser = new User();
        newUser.setFullName("nandang papa");

        given(userRepository.findById(anyLong()))
                .willReturn(Optional.ofNullable(null));

        userService.updateUser(user.getId(), newUser);
    }

    @Test
    public void showUserDetail_whenUserFound() {
        User user = new User();
        user.setId(69L);
        user.setFullName("nandang");
        user.setEmail("net.nandang@gmail.com");
        user.setPassword("password");

        when(userRepository.findById(user.getId()))
                .thenReturn(Optional.of(user));

        User expected = userService.showUserDetail(user.getId());

        assertThat(expected).isSameAs(user);
        verify(userRepository).findById(user.getId());
    }

    @Test(expected = UserNotFoundException.class)
    public void shouldThrowException_whenUserNotFound_onShowUserDetail() {
        User user = new User();
        user.setId(69L);
        user.setFullName("nandang");
        user.setEmail("net.nandang@gmail.com");
        user.setPassword("password");

        given(userRepository.findById(anyLong()))
                .willReturn(Optional.ofNullable(null));
        userService.showUserDetail(user.getId());
    }
}
