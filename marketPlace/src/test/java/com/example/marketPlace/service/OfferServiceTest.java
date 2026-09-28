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
    void canMakeOneOfferToProduct(){
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
        offer.setOfferedPrice(BigDecimal.valueOf(-1000));
        offer.setUser(user);
        offer.setProduct(product);

        when(offerRepository.findByUserIdAndProductId(
                user.getId(),product.getId())).thenReturn(Optional.empty());

        offerService.makeOffer(offer);

        verify(offerRepository).save(offer);
    }
}