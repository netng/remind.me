package com.training.alterra.miniproject.remindmeapp.services.users;

import com.training.alterra.miniproject.remindmeapp.entities.User;

import java.util.List;
import java.util.Optional;

public interface IUserService {
    User createNewUser(User user);

    List<User> listAllUsers();

    void deleteUser(Long userId);

    User updateUser(Long userId, User user);

    User showUserDetail(Long userId);
}
