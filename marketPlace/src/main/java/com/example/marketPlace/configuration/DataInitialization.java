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
            OfferRepository offerRepository,
            PasswordEncoder passwordEncoder) {

        return args -> {

            User user = userRepository
                    .findByEmail("joao@email.com")
                    .orElseGet(() -> {
                        User newUser = new User();
                        newUser.setName("João");
                        newUser.setEmail("joao@email.com");
                        newUser.setPassword(
                                passwordEncoder.encode("123456")
                        );

                        return userRepository.save(newUser);
                    });

            if (productRepository.count() == 0) {

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

            User seller = userRepository
                    .findByEmail("maria@email.com")
                    .orElseGet(() -> {
                        User newUser = new User();
                        newUser.setName("Maria");
                        newUser.setEmail("maria@email.com");
                        newUser.setPassword(
                                passwordEncoder.encode("123456")
                        );

                        return userRepository.save(newUser);
                    });

            Product productForOffer = productRepository.findAll()
                    .stream()
                    .filter(product ->
                            !product.getUser().getId()
                                    .equals(seller.getId()))
                    .findFirst()
                    .orElse(null);

            if (productForOffer != null
                    && offerRepository.findByUserIdAndProductId(
                    seller.getId(),
                    productForOffer.getId()
            ).isEmpty()) {

                Offer offer = new Offer();
                offer.setUser(seller);
                offer.setProduct(productForOffer);
                offer.setOfferedPrice(
                        BigDecimal.valueOf(2300)
                );

                offerRepository.save(offer);
            }
        };
    }
}