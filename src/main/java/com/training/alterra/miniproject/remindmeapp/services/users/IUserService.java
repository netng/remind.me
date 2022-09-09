package com.training.alterra.miniproject.remindmeapp.services.users;

import com.training.alterra.miniproject.remindmeapp.dtos.BaseResponseDTO;
import com.training.alterra.miniproject.remindmeapp.dtos.users.UserRequestDTO;
import com.training.alterra.miniproject.remindmeapp.dtos.users.UserResponseDTO;
import com.training.alterra.miniproject.remindmeapp.entities.User;

import java.util.List;
import java.util.Optional;

public interface IUserService {
    //User createNewUser(User user);
    BaseResponseDTO<String, String, UserResponseDTO> createNewUser(UserRequestDTO requestDTO);

    BaseResponseDTO<String, String, List<UserResponseDTO>> listAllUsers();

    BaseResponseDTO<String, String, String> deleteUser(Long userId);

    BaseResponseDTO<String, String, UserResponseDTO> updateUser(Long userId, UserRequestDTO requestDTO);

    BaseResponseDTO<String, String, UserResponseDTO> showUserDetail(Long userId);
}
