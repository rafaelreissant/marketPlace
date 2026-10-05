package com.example.marketPlace.service;

import com.example.marketPlace.model.Offer;
import com.example.marketPlace.model.Product;
import com.example.marketPlace.model.User;
import com.example.marketPlace.repository.OfferRepository;
import com.example.marketPlace.repository.ProductRepository;
import com.example.marketPlace.repository.UserRepository;
import com.example.marketPlace.service.authencitcate.AuthenticatedUserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OfferServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private AuthenticatedUserService authenticatedUserService;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private OfferRepository offerRepository;

    @InjectMocks
    private OfferService offerService;

    @Test
    void canMakeOneOfferToProduct() {

        User user = new User();
        user.setId(UUID.fromString("c92b118d-f72d-4f00-ac87-1bbce53e04a2"));
        user.setName("test");
        user.setPassword("123");
        user.setEmail("test@gmail.com");

        userRepository.save(user);

        when(authenticatedUserService.getUserAuthenticated())
                .thenReturn(user);

        Product product = new Product();
        product.setId(UUID.fromString("3f06213a-322f-439a-addd-40f2ab1c2c9b"));
        product.setName("Laptop");
        product.setDescription("Surprise");
        product.setPrice(BigDecimal.valueOf(1000));
        product.setUser(user);

        productRepository.save(product);

        Offer offer = new Offer();
        offer.setId(UUID.fromString("1efbd102-82a6-4420-9163-c38b6744268b"));
        offer.setOfferedPrice(BigDecimal.valueOf(900));
        offer.setUser(user);
        offer.setProduct(product);

        offerService.makeOffer(offer);

        verify(offerRepository).save(offer);
    }
}