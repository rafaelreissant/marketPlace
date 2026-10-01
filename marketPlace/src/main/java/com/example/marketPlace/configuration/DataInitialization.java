package com.example.marketPlace.configuration;

import com.example.marketPlace.model.Product;
import com.example.marketPlace.model.User;
import com.example.marketPlace.repository.ProductRepository;
import com.example.marketPlace.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Configuration
@Transactional
public class DataInitialization {

    @Bean
    CommandLineRunner initDatabase(
            UserRepository userRepository,
            ProductRepository productRepository,
            PasswordEncoder passwordEncoder) {

        return args -> {

            if (userRepository.count() == 0) {

                User user = new User();
                user.setName("João");
                user.setEmail("joao@email.com");
                user.setPassword(
                        passwordEncoder.encode("123456")
                );

                userRepository.save(user);
            }

            if (productRepository.count() == 0) {

                User user = userRepository
                        .findByEmail("joao@email.com")
                        .orElseThrow(() ->
                                new RuntimeException("User not found"));

                Product product1 = new Product();
                product1.setName("Notebook");
                product1.setDescription(
                        "Notebook usado em bom estado"
                );
                product1.setPrice(BigDecimal.valueOf(2500));
                product1.setUser(user);

                Product product2 = new Product();
                product2.setName("Monitor");
                product2.setDescription(
                        "Monitor 24 polegadas"
                );
                product2.setPrice(BigDecimal.valueOf(800));
                product2.setUser(user);

                productRepository.saveAll(
                        List.of(product1, product2)
                );
            }
        };
    }
}