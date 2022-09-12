package com.training.alterra.miniproject.remindmeapp.controllers;

import com.training.alterra.miniproject.remindmeapp.dtos.users.UserRequestDTO;
import com.training.alterra.miniproject.remindmeapp.dtos.users.UserResponseDTO;
import com.training.alterra.miniproject.remindmeapp.entities.Role;
import com.training.alterra.miniproject.remindmeapp.entities.RoleName;
import com.training.alterra.miniproject.remindmeapp.entities.User;
import com.training.alterra.miniproject.remindmeapp.exceptions.AppException;
import com.training.alterra.miniproject.remindmeapp.payloads.*;
import com.training.alterra.miniproject.remindmeapp.repositories.RoleRepository;
import com.training.alterra.miniproject.remindmeapp.repositories.UserRepository;
import com.training.alterra.miniproject.remindmeapp.securities.JwtTokenProvider;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtTokenProvider tokenProvider;

    @Autowired
    ModelMapper modelMapper;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody SignInRequest signInRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        signInRequest.getUsernameOrEmail(),
                        signInRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.generateToken(authentication);
        return ResponseEntity.ok(new ApiResponse("OK", "success", new JwtAuthenticationResponse(jwt)));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return new ResponseEntity(new ApiResponse("BAD_REQUEST", "Username already taken!", Collections.emptyList()),
                    HttpStatus.BAD_REQUEST);
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return new ResponseEntity(new ApiResponse("BAD_REQUEST", "Email already in use", Collections.emptyList()),
                    HttpStatus.BAD_REQUEST);
        }

        UserRequestDTO user = new UserRequestDTO(signUpRequest.getFullName(), signUpRequest.getUsername(),
                signUpRequest.getEmail(), signUpRequest.getPassword(), signUpRequest.getTimeZone());

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        Role userRole = roleRepository.findByName(RoleName.ROLE_USER)
                .orElseThrow(() -> new AppException("User Role not set"));

        user.setRoles(Collections.singleton(userRole));

        User result = userRepository.save(modelMapper.map(user, User.class));
        UserResponseDTO response = modelMapper.map(result, UserResponseDTO.class);

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/api/v1/users/{username}")
                .buildAndExpand(result.getUsername()).toUri();

        return ResponseEntity.created(location).body(new ApiResponse("OK", "user registered successfully", response));
    }
}
