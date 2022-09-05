package com.training.alterra.miniproject.remindmeapp.services.users;

import com.training.alterra.miniproject.remindmeapp.entities.User;
import com.training.alterra.miniproject.remindmeapp.repositories.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @Mock
    UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    public void createNewUser_shouldReturnNewCreatedUser() {
        User user = new User();
        user.setFullName("nandang super papa");
        user.setEmail("net.nandang@gmail.com");
        user.setPassword("password");

        when(userRepository.save(ArgumentMatchers.any(User.class)))
                .thenReturn(user);

        User userCreated = userService.createNewUser(user);

        assertThat(userCreated.getFullName())
                .isSameAs(user.getFullName());

        assertThat(userCreated.getEmail())
                .isSameAs(user.getEmail());

        verify(userRepository).save(user);

    }

    @Test
    public void listAllUsers_shouldReturnListOfUsers() {
        List<User> users = new ArrayList<>();
        users.add(new User());

        given(userRepository.findAll()).willReturn(users);

        List<User> expected = userService.listAllUsers();

        assertEquals(expected, users);
        verify(userRepository).findAll();
    }

    @Test
    public void deleteUser_ifGivenIdFound() {
        User user = new User();
        user.setFullName("nandang");
        user.setPassword("password");
        user.setEmail("net.nandang@gmail.com");
        user.setId(1L);

        when(userRepository.findById(user.getId()))
                .thenReturn(Optional.of(user));

        userService.deleteUser(user.getId());
        verify(userRepository).deleteById(user.getId());
    }
}
