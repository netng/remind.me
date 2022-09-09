/**
 * @Author Nandang Sopyan
 * @ApplicationName remind.me app
 * @CreatedAt Sept 2022
 * @Description This is a REST API application as mini project task at alterra training academy program
 */
package com.training.alterra.miniproject.remindmeapp.services.users;

import com.training.alterra.miniproject.remindmeapp.dtos.BaseResponseDTO;
import com.training.alterra.miniproject.remindmeapp.dtos.PaginatedBaseResponseDTO;
import com.training.alterra.miniproject.remindmeapp.dtos.users.UserRequestDTO;
import com.training.alterra.miniproject.remindmeapp.dtos.users.UserResponseDTO;
import com.training.alterra.miniproject.remindmeapp.entities.User;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface IUserService {
    //User createNewUser(User user);
    BaseResponseDTO<String, String, UserResponseDTO> createNewUser(UserRequestDTO requestDTO);

    PaginatedBaseResponseDTO<String, String, List<UserResponseDTO>> listAllUsers(Pageable pageable);

    BaseResponseDTO<String, String, String> deleteUser(Long userId);

    BaseResponseDTO<String, String, UserResponseDTO> updateUser(Long userId, UserRequestDTO requestDTO);

    BaseResponseDTO<String, String, UserResponseDTO> showUserDetail(Long userId);
}