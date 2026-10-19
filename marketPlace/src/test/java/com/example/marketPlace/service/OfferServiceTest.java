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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

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
        User buyer = new User();
        buyer.setId(UUID.fromString("c92b118d-f72d-4f00-ac87-1bbce53e04a2"));
        buyer.setName("buyer");
        buyer.setPassword("123");
        buyer.setEmail("buyer@gmail.com");

        when(authenticatedUserService.getUserAuthenticated())
                .thenReturn(buyer);

        User seller = new User();
        seller.setId(UUID.fromString("a92b118d-f72d-4f00-ac87-1bbce53e04a2"));
        seller.setName("seller");
        seller.setPassword("123");
        seller.setEmail("seller@gmail.com");

        Product product = new Product();
        product.setId(UUID.fromString("3f06213a-322f-439a-addd-40f2ab1c2c9b"));
        product.setName("Laptop");
        product.setDescription("Surprise");
        product.setPrice(BigDecimal.valueOf(1000));
        product.setUser(seller);

        when(productRepository.findById(product.getId()))
                .thenReturn(Optional.of(product));

        when(offerRepository.findByUserIdAndProductId(
                buyer.getId(), product.getId()))
                .thenReturn(Optional.empty());

        Offer offer = new Offer();
        offer.setOfferedPrice(BigDecimal.valueOf(900));

        offerService.makeOffer(offer, product.getId());

        assertEquals(buyer, offer.getUser());
        assertEquals(product, offer.getProduct());

        verify(offerRepository).save(offer);
    }

    @Test
    void cantMakeOneOfferToProduct() {
        User buyer = new User();
        buyer.setId(UUID.fromString("c92b118d-f72d-4f00-ac87-1bbce53e04a2"));
        buyer.setName("buyer");
        buyer.setPassword("123");
        buyer.setEmail("buyer@gmail.com");

        User seller = new User();
        seller.setId(UUID.fromString("a92b118d-f72d-4f00-ac87-1bbce53e04a2"));
        seller.setName("seller");
        seller.setPassword("123");
        seller.setEmail("seller@gmail.com");

        Product product = new Product();
        product.setId(UUID.fromString("3f06213a-322f-439a-addd-40f2ab1c2c9b"));
        product.setName("Laptop");
        product.setDescription("Surprise");
        product.setPrice(BigDecimal.valueOf(1000));
        product.setUser(seller);

        when(authenticatedUserService.getUserAuthenticated())
                .thenReturn(buyer);

        when(productRepository.findById(product.getId()))
                .thenReturn(Optional.of(product));

        when(offerRepository.findByUserIdAndProductId(
                buyer.getId(), product.getId()))
                .thenReturn(Optional.of(new Offer()));

        Offer offer = new Offer();
        offer.setOfferedPrice(BigDecimal.valueOf(900));

        RuntimeException exception = assertThrows(
                RuntimeException.class,
                () -> offerService.makeOffer(offer, product.getId())
        );

        assertEquals(
                "User already made an offer for this product",
                exception.getMessage()
        );

        verify(offerRepository, never()).save(any(Offer.class));
    }

}