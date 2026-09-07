package com.example.marketPlace.repository;

import com.example.marketPlace.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void createUser(){
        User user = new User();
        user.setName("Test");
        user.setPassword("123");
        user.setEmail("test@gmail.com");

        userRepository.save(user);

        assertNotNull(user.getId());
    }

    @Test
    void getUserById(){
        User user = new User();
        user.setName("Test");
        user.setPassword("123");
        user.setEmail("test@gmail.com");

        User userSaved = userRepository.save(user);

        User userFound = userRepository.findById(userSaved.getId()).orElseThrow();

        assertEquals(userSaved.getId(), userFound.getId());
    }

    @Test
    void getAllUsers(){
        User user1 = new User();
        user1.setName("Test");
        user1.setPassword("123");
        user1.setEmail("test@gmail.com");

        User user2 = new User();
        user2.setName("Sucess");
        user2.setPassword("456");
        user2.setEmail("prototype@gmail.com");

        userRepository.save(user1);
        userRepository.save(user2);

        List<User> userList = userRepository.findAll();

        assertNotNull(userList);
        assertEquals(2, userList.size());
    }
}