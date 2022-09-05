package com.training.alterra.miniproject.remindmeapp.services.users;

import com.training.alterra.miniproject.remindmeapp.entities.User;
import com.training.alterra.miniproject.remindmeapp.exceptions.UserNotFoundException;
import com.training.alterra.miniproject.remindmeapp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IUserService{

    @Autowired
    UserRepository userRepository;
    @Override
    public User createNewUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> listAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public void deleteUser(Long userId) {
        userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));

        userRepository.deleteById(userId);
    }

    @Override
    public User updateUser(Long userId, User user) {
        userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));

        user.setId(userId);
        return userRepository.save(user);
    }

    @Override
    public User showUserDetail(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));
    }

}
