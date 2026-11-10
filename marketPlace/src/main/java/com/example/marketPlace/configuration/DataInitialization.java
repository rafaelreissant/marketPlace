package com.example.marketPlace.configuration;

import com.example.marketPlace.model.Offer;
import com.example.marketPlace.model.Product;
import com.example.marketPlace.model.User;
import com.example.marketPlace.repository.OfferRepository;
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
public class DataInitialization {

    @Bean
    CommandLineRunner initDatabase(
            UserRepository userRepository,
            ProductRepository productRepository,
            PasswordEncoder passwordEncoder) {

        return args -> {

            User joao = userRepository
                    .findByEmail("joao@email.com")
                    .orElseGet(() -> {
                        User user = new User();
                        user.setName("João");
                        user.setEmail("joao@email.com");
                        user.setPassword(
                                passwordEncoder.encode("123456")
                        );
                        return userRepository.save(user);
                    });

            User maria = userRepository
                    .findByEmail("maria@email.com")
                    .orElseGet(() -> {
                        User user = new User();
                        user.setName("Maria");
                        user.setEmail("maria@email.com");
                        user.setPassword(
                                passwordEncoder.encode("123456")
                        );
                        return userRepository.save(user);
                    });

            if (productRepository.count() == 0) {

                List<Product> products = List.of(
                        createProduct(
                                "Notebook Dell",
                                "Notebook Dell usado em bom estado",
                                2500, joao
                        ),
                        createProduct(
                                "Notebook Lenovo",
                                "Notebook Lenovo com 16GB de RAM",
                                3200, maria
                        ),
                        createProduct(
                                "Monitor LG",
                                "Monitor LG de 24 polegadas",
                                800, joao
                        ),
                        createProduct(
                                "Monitor Samsung",
                                "Monitor Samsung de 27 polegadas",
                                1200, maria
                        ),
                        createProduct(
                                "Teclado Mecânico",
                                "Teclado mecânico RGB",
                                350, joao
                        ),
                        createProduct(
                                "Mouse Logitech",
                                "Mouse sem fio Logitech",
                                150, maria
                        ),
                        createProduct(
                                "Headset Gamer",
                                "Headset gamer com microfone",
                                450, joao
                        ),
                        createProduct(
                                "Cadeira Gamer",
                                "Cadeira gamer ergonômica",
                                1800, maria
                        ),
                        createProduct(
                                "Webcam Logitech",
                                "Webcam Full HD",
                                280, joao
                        ),
                        createProduct(
                                "SSD 1TB",
                                "SSD de 1TB",
                                600, maria
                        )
                );

                productRepository.saveAll(products);
            }
        };
    }

    private Product createProduct(
            String name,
            String description,
            double price,
            User user) {

        Product product = new Product();
        product.setName(name);
        product.setDescription(description);
        product.setPrice(BigDecimal.valueOf(price));
        product.setUser(user);

        return product;
    }
}