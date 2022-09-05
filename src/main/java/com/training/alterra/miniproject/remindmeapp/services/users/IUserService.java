package com.training.alterra.miniproject.remindmeapp.services.users;

import com.training.alterra.miniproject.remindmeapp.entities.User;

import java.util.List;

public interface IUserService {
    User createNewUser(User user);

    List<User> listAllUsers();

    void deleteUser(Long userId);
}
