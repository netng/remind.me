package com.training.alterra.miniproject.remindmeapp.services.users;

import com.training.alterra.miniproject.remindmeapp.dtos.users.UserRequestDTO;
import com.training.alterra.miniproject.remindmeapp.dtos.users.UserResponseDTO;
import com.training.alterra.miniproject.remindmeapp.entities.User;

import java.util.List;
import java.util.Optional;

public interface IUserService {
    //User createNewUser(User user);
    UserResponseDTO createNewUser(UserRequestDTO requestDTO);

    List<UserResponseDTO> listAllUsers();

    void deleteUser(Long userId);

    UserResponseDTO updateUser(Long userId, UserRequestDTO requestDTO);

    User showUserDetail(Long userId);
}
