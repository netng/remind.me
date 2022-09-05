package com.training.alterra.miniproject.remindmeapp.services.users;

import com.training.alterra.miniproject.remindmeapp.entities.User;
import com.training.alterra.miniproject.remindmeapp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
