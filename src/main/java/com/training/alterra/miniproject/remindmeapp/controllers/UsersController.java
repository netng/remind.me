package com.training.alterra.miniproject.remindmeapp.controllers;

import com.fasterxml.jackson.databind.ser.Serializers;
import com.training.alterra.miniproject.remindmeapp.dtos.BaseResponseDTO;
import com.training.alterra.miniproject.remindmeapp.dtos.PaginatedBaseResponseDTO;
import com.training.alterra.miniproject.remindmeapp.dtos.users.UserRequestDTO;
import com.training.alterra.miniproject.remindmeapp.dtos.users.UserResponseDTO;
import com.training.alterra.miniproject.remindmeapp.entities.User;
import com.training.alterra.miniproject.remindmeapp.services.users.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class UsersController {

    @Autowired
    IUserService userService;

    @PostMapping("/users/signup")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<BaseResponseDTO<String, String, UserResponseDTO>> createNewUser(@RequestBody UserRequestDTO userRequestDTO) {
        BaseResponseDTO<String, String, UserResponseDTO> response = userService.createNewUser(userRequestDTO);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/users")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<PaginatedBaseResponseDTO<String, String, List<UserResponseDTO>>> listAllUsers(Pageable pageable) {
        PaginatedBaseResponseDTO<String, String, List<UserResponseDTO>> response = userService.listAllUsers(pageable);
        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping("/users/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<BaseResponseDTO<String, String, String>> deleteUser(@PathVariable Long userId) {
        BaseResponseDTO<String, String, String> response = userService.deleteUser(userId);
        return ResponseEntity.ok().body(response);
    }

    @PutMapping("/users/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<BaseResponseDTO<String, String, UserResponseDTO>> updateUser(@PathVariable Long userId, @RequestBody UserRequestDTO requestDTO) {
        return ResponseEntity.ok().body(userService.updateUser(userId, requestDTO));
    }

    @GetMapping("/users/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<BaseResponseDTO<String, String, UserResponseDTO>> showUserDetail(@PathVariable Long userId) {
        return ResponseEntity.ok().body(userService.showUserDetail(userId));
    }
}
