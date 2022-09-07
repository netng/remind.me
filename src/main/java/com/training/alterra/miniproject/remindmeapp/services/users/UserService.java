package com.training.alterra.miniproject.remindmeapp.services.users;

import com.training.alterra.miniproject.remindmeapp.dtos.users.UserRequestDTO;
import com.training.alterra.miniproject.remindmeapp.dtos.users.UserResponseDTO;
import com.training.alterra.miniproject.remindmeapp.entities.User;
import com.training.alterra.miniproject.remindmeapp.exceptions.ResourceNotFoundException;
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
    public UserResponseDTO createNewUser(UserRequestDTO requestDTO) {
        User user = convertToEntity(requestDTO);
        User userCreated = userRepository.save(user);
        return convertToDto(userCreated);
    }

    @Override
    public List<UserResponseDTO> listAllUsers() {
        List<User> users = userRepository.findAll();
        if (!users.isEmpty()) {
            return users.stream()
                    .map(user -> modelMapper.map(user, UserResponseDTO.class))
                    .collect(Collectors.toList());
        }

        return Collections.emptyList();

    }

    @Override
    public void deleteUser(Long userId) {
        userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException(userId));

        userRepository.deleteById(userId);
    }

    @Override
    /**
     * NOTE:
     * Bean Utils Properties
     * Transactional
     */
    public UserResponseDTO updateUser(Long userId, UserRequestDTO requestDTO) {
        userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException(userId));

        User userUpdate = convertToEntity(requestDTO);
        userUpdate.setId(userId);
        User updatedUser = userRepository.save(userUpdate);
        return convertToDto(updatedUser);

    }

    @Override
    public UserResponseDTO showUserDetail(Long userId) {
        userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException(userId));
        Optional<User> user = userRepository.findById(userId);
        return convertToDto(Optional.of(user).get().get());
    }

    private User convertToEntity(UserRequestDTO requestDTO) {
        return modelMapper.map(requestDTO, User.class);
    }

    private UserResponseDTO convertToDto(User user) {
        return modelMapper.map(user, UserResponseDTO.class);
    }

}
