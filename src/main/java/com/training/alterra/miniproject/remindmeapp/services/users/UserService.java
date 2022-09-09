package com.training.alterra.miniproject.remindmeapp.services.users;

import com.training.alterra.miniproject.remindmeapp.dtos.BaseResponseDTO;
import com.training.alterra.miniproject.remindmeapp.dtos.users.UserRequestDTO;
import com.training.alterra.miniproject.remindmeapp.dtos.users.UserResponseDTO;
import com.training.alterra.miniproject.remindmeapp.entities.User;
import com.training.alterra.miniproject.remindmeapp.exceptions.EntityNotFoundException;
import com.training.alterra.miniproject.remindmeapp.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
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
        return new BaseResponseDTO<>("OK", "Succesfully created data", convertToDto(userCreated));
    }

    @Override
    public BaseResponseDTO<String, String, List<UserResponseDTO>> listAllUsers() {
        List<User> users = userRepository.findAll();
        if (!users.isEmpty()) {
            List<UserResponseDTO> usersDTO =  users.stream()
                    .map(user -> modelMapper.map(user, UserResponseDTO.class))
                    .collect(Collectors.toList());

            BaseResponseDTO<String, String, List<UserResponseDTO>> responseDTO = new BaseResponseDTO<>(
                    "OK", "Successfully retrieving data", usersDTO
            );

            return responseDTO;
        }

        return new BaseResponseDTO<>("OK", "Data is empty", Collections.emptyList());

    }

    @Override
    public BaseResponseDTO<String, String, String> deleteUser(Long userId) {
        userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException(userId));

        userRepository.deleteById(userId);
        return new BaseResponseDTO<>("OK", "Successfully deleting data", "ok");
    }

    @Override
    /**
     * NOTE:
     * Bean Utils Properties
     * Transactional
     */
    public BaseResponseDTO<String, String, UserResponseDTO> updateUser(Long userId, UserRequestDTO requestDTO) {
        userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException(userId));

        User userUpdate = convertToEntity(requestDTO);
        userUpdate.setId(userId);
        User updatedUser = userRepository.save(userUpdate);
        return new BaseResponseDTO<>("OK", "Successfully updating data", convertToDto(updatedUser));

    }

    @Override
    public BaseResponseDTO<String, String, UserResponseDTO> showUserDetail(Long userId) {
        userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException(userId));
        Optional<User> user = userRepository.findById(userId);
        return new BaseResponseDTO<>("OK", "Success", convertToDto(user.get()));
    }

    private User convertToEntity(UserRequestDTO requestDTO) {
        return modelMapper.map(requestDTO, User.class);
    }

    private UserResponseDTO convertToDto(User user) {
        return modelMapper.map(user, UserResponseDTO.class);
    }

}
