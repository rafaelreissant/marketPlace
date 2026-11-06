package com.example.marketPlace.service;

import com.example.marketPlace.error.exceptions.EmailInUse;
import com.example.marketPlace.error.exceptions.UserNotFound;
import com.example.marketPlace.model.User;
import com.example.marketPlace.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void saveUser(User user){
        if(userRepository.existsByEmail(user.getEmail())){
            throw new EmailInUse("Email already in use");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        userRepository.save(user);
    }

    public User getUserById(UUID id){
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFound(" user not found"));
    }

    public List<User> getAllUser(){
        return userRepository.findAll();
    }
}
