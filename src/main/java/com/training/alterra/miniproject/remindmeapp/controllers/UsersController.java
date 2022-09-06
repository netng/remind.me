package com.training.alterra.miniproject.remindmeapp.controllers;

import com.training.alterra.miniproject.remindmeapp.dtos.users.UserRequestDTO;
import com.training.alterra.miniproject.remindmeapp.dtos.users.UserResponseDTO;
import com.training.alterra.miniproject.remindmeapp.entities.User;
import com.training.alterra.miniproject.remindmeapp.services.users.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ResponseEntity<UserResponseDTO> createNewUser(@RequestBody UserRequestDTO userRequestDTO) {
        UserResponseDTO userResponseDTO = userService.createNewUser(userRequestDTO);
        return ResponseEntity.ok().body(userResponseDTO);
    }

    @GetMapping("/users")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<UserResponseDTO>> listAllUsers() {
        List<UserResponseDTO> users = userService.listAllUsers();
        return ResponseEntity.ok().body(users);
    }

    @DeleteMapping("/users/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
    }

    @PutMapping("/users/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<UserResponseDTO> updateUser(@PathVariable Long userId, @RequestBody UserRequestDTO requestDTO) {
        return ResponseEntity.ok().body(userService.updateUser(userId, requestDTO));
    }

    @GetMapping("/users/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<UserResponseDTO> showUserDetail(@PathVariable Long userId) {
        return ResponseEntity.ok().body(userService.showUserDetail(userId));
    }
}
