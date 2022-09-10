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
import com.training.alterra.miniproject.remindmeapp.exceptions.EntityNotFoundException;
import com.training.alterra.miniproject.remindmeapp.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService implements IUserService{

    @Autowired
    UserRepository userRepository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public BaseResponseDTO<String, String, UserResponseDTO> createNewUser(UserRequestDTO requestDTO) {
        User user = convertToEntity(requestDTO);
        User userCreated = userRepository.save(user);

        return new BaseResponseDTO<>(
                "OK",
                "Succesfully created data",
                convertToDto(userCreated)
        );
    }

    @Override
    public PaginatedBaseResponseDTO<String, String, List<UserResponseDTO>> listAllUsers(Pageable pageable) {
        Page<User> users = userRepository.findAll(pageable);
        if (!users.isEmpty()) {
            List<UserResponseDTO> usersDTO =  users.stream()
                    .map(user -> modelMapper.map(user, UserResponseDTO.class))
                    .collect(Collectors.toList());

            PaginatedBaseResponseDTO<String, String, List<UserResponseDTO>> responseDTO =
                    new PaginatedBaseResponseDTO<>(
                    "OK",
                   "Successfully retrieving data",
                    usersDTO,
                    users.getTotalElements(),
                    users.getTotalPages(),
                    users.getNumber() + 1
            );

            return responseDTO;
        }

        return new PaginatedBaseResponseDTO<>(
               "OK",
               "Data is empty",
                Collections.emptyList(),
                users.getTotalElements(),
                users.getTotalPages(),
                users.getNumber() + 1
        );
    }

    @Override
    public BaseResponseDTO<String, String, String> deleteUser(Long userId) {
        userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException(userId));

        userRepository.deleteById(userId);

        return new BaseResponseDTO<>(
                "OK",
                "Successfully deleting data",
                null
        );
    }

    @Override
    public BaseResponseDTO<String, String, UserResponseDTO> updateUser(Long userId, UserRequestDTO requestDTO) {
        userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException(userId));

        User userUpdate = convertToEntity(requestDTO);
        userUpdate.setId(userId);
        User updatedUser = userRepository.save(userUpdate);

        return new BaseResponseDTO<>(
                "OK",
                "Successfully updating data",
                convertToDto(updatedUser)
        );
    }

    @Override
    public BaseResponseDTO<String, String, UserResponseDTO> showUserDetail(Long userId) {
        userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException(userId));
        Optional<User> user = userRepository.findById(userId);

        return new BaseResponseDTO<>(
                "OK",
                "Success",
                convertToDto(user.get())
        );
    }

    private User convertToEntity(UserRequestDTO requestDTO) {
        return modelMapper.map(requestDTO, User.class);
    }

    private UserResponseDTO convertToDto(User user) {
        return modelMapper.map(user, UserResponseDTO.class);
    }
}