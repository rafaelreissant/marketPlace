package com.example.marketPlace.service;

import com.example.marketPlace.model.User;
import com.example.marketPlace.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void saveUser(User user){
        userRepository.save(user);
    }

    public User getUserById(UUID id){
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(" user not found"));
    }

    public List<User> getAllUser(){
        return userRepository.findAll();
    }
}
