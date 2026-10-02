package com.example.marketPlace.service;

import com.example.marketPlace.model.Offer;
import com.example.marketPlace.model.Product;
import com.example.marketPlace.model.User;
import com.example.marketPlace.repository.OfferRepository;
import com.example.marketPlace.repository.ProductRepository;
import com.example.marketPlace.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.math.BigDecimal;
import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OfferServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private OfferRepository offerRepository;

    @InjectMocks
    private OfferService offerService;

    @Test
    void canMakeOneOfferToProduct() {

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

        when(productRepository.findById(product.getId()))
                .thenReturn(Optional.of(product));

        Offer offer = new Offer();
        offer.setOfferedPrice(BigDecimal.valueOf(900));

        when(userRepository.findByEmail(user.getEmail()))
                .thenReturn(Optional.of(user));

        Authentication authentication =
                new UsernamePasswordAuthenticationToken(
                        user.getEmail(),
                        null
                );

        SecurityContextHolder.getContext()
                .setAuthentication(authentication);

        offerService.makeOffer(offer, product.getId());

        verify(offerRepository).save(offer);

        SecurityContextHolder.clearContext();
    }
}