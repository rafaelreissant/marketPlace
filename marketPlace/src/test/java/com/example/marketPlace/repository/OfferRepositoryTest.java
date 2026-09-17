package com.example.marketPlace.repository;

import com.example.marketPlace.model.Offer;
import com.example.marketPlace.model.Product;
import com.example.marketPlace.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertNotNull;


@DataJpaTest
class OfferRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OfferRepository offerRepository;

    @Test
    void shouldSaveOffer(){
        User user = new User();
        user.setName("test");
        user.setPassword("123");
        user.setEmail("test@gmail.com");

        userRepository.save(user);

        Product product = new Product();
        product.setName("Laptop");
        product.setDescription("Surprise");
        product.setPrice(BigDecimal.valueOf(1000));
        product.setUser(user);

        productRepository.save(product);

        Offer offer = new Offer();
        offer.setOfferedPrice(null);
        offer.setUser(user);
        offer.setProduct(product);

        offerRepository.save(offer);

        assertNotNull(offer.getId());
    }
}