package com.example.marketPlace.configuration;

import com.example.marketPlace.model.User;
import com.example.marketPlace.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Configuration
@Transactional
public class DataInitialization {

    @Bean
    CommandLineRunner initDataBase(UserRepository userRepository) {
        return args -> {
            if (userRepository.count() == 0) {

                User user1 = new User();
                user1.setName("João");
                user1.setEmail("joao@email.com");
                user1.setPassword("123456");

                User user2 = new User();
                user2.setName("Maria");
                user2.setEmail("maria@email.com");
                user2.setPassword("123456");

                User user3 = new User();
                user3.setName("Pedro");
                user3.setEmail("pedro@email.com");
                user3.setPassword("abcdef");

                userRepository.saveAll(
                        List.of(user1, user2, user3)
                );
            }
        };
    }
}