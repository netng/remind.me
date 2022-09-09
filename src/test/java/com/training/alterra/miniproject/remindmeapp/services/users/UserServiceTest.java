package com.training.alterra.miniproject.remindmeapp.services.users;

import com.training.alterra.miniproject.remindmeapp.dtos.BaseResponseDTO;
import com.training.alterra.miniproject.remindmeapp.dtos.PaginatedBaseResponseDTO;
import com.training.alterra.miniproject.remindmeapp.dtos.users.UserRequestDTO;
import com.training.alterra.miniproject.remindmeapp.dtos.users.UserResponseDTO;
import com.training.alterra.miniproject.remindmeapp.entities.User;
import com.training.alterra.miniproject.remindmeapp.exceptions.EntityNotFoundException;
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


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    UserService userService = spy(new UserService());

    ModelMapper modelMapper = spy(new ModelMapper());

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }
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

        BaseResponseDTO<String, String, UserResponseDTO> userCreated = userService.createNewUser(requestDTO);

        assertThat(userCreated.getData().getId())
                .isNotNull();

        assertThat(userCreated.getData().getFullName())
                .isSameAs(user.getFullName());

        assertThat(userCreated.getData().getEmail())
                .isSameAs(user.getEmail());
    }

    @Test
    public void listAllUsers_shouldReturnListOfUsers() {
        List<User> users = new ArrayList<>();
        users.add(new User());

        given(userRepository.findAll())
                .willReturn(users);

        List<UserResponseDTO> responseDto = users.stream()
                .map(user -> modelMapper.map(user, UserResponseDTO.class))
                .collect(Collectors.toList());

        BaseResponseDTO<String, String, List<UserResponseDTO>> response = new BaseResponseDTO<>(
                "OK", "Sucsessfully retrieving data", responseDto
        );

        PaginatedBaseResponseDTO<String, String, List<UserResponseDTO>> expected = userService.listAllUsers((Pageable) users);

        assertThat(expected.equals(response));
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


        BaseResponseDTO<String, String, String> expected = new BaseResponseDTO<>(
                "OK", "Successfully deleted data", ""
        );
        BaseResponseDTO<String, String, String> response = userService.deleteUser(user.getId());

        assertThat(expected.equals(response));
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

    @Test(expected = EntityNotFoundException.class)
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
        BaseResponseDTO<String, String, UserResponseDTO> expected = new BaseResponseDTO<>(
                "OK", "Success", currentUser
        );

        BaseResponseDTO<String, String, UserResponseDTO> response = userService.showUserDetail(user.getId());

        assertThat(expected.equals(response));
    }

    @Test(expected = EntityNotFoundException.class)
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
}
