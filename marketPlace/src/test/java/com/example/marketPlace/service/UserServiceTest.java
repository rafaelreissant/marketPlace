package com.example.marketPlace.service;

import com.example.marketPlace.model.User;
import com.example.marketPlace.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    void saveUserWithUnsaveEmail(){
        User user = new User();
        user.setName("Test");
        user.setPassword("123456");
        user.setEmail("test@gmail.com");

        when(userRepository.existsByEmail(user.getEmail())).thenReturn(false);

        userService.saveUser(user);

        verify(userRepository).save(user);
    }

    @Test
    void saveUserWithSavedEmail(){
        User user = new User();
        user.setName("Test");
        user.setPassword("123456");
        user.setEmail("test@gmail.com");

        when(userRepository.existsByEmail(user.getEmail())).thenReturn(true);

        assertThrows(RuntimeException.class, () ->  userService.saveUser(user));

        verify(userRepository, never()).save(user);
    }
}