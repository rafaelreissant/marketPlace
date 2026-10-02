package com.example.marketPlace.service;

import com.example.marketPlace.model.Offer;
import com.example.marketPlace.model.Product;
import com.example.marketPlace.model.User;
import com.example.marketPlace.repository.OfferRepository;
import com.example.marketPlace.repository.ProductRepository;
import com.example.marketPlace.repository.UserRepository;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class OfferService {

    private final OfferRepository offerRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    public OfferService(OfferRepository offerRepository, UserRepository userRepository, ProductRepository productRepository) {
        this.offerRepository = offerRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    public void makeOffer(Offer offer, UUID productId){

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        String email = authentication.getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        offer.setUser(user);
        offer.setProduct(product);

        offerRepository.save(offer);
    }

    public Offer getOfferById(UUID uuid){

        @Nullable Authentication authentication = SecurityContextHolder
                                                    .getContext().getAuthentication();

        String email = authentication.getName();

        User user = userRepository
                .findByEmail(email).orElseThrow(() -> new RuntimeException(" User not found"));

        return offerRepository.findByUserIdAndProductId(uuid, user.getId())
                .orElseThrow(() -> new RuntimeException(" offer not found"));
    }

    public List<Offer> getAllOffer(){
        return offerRepository.findAll();
    }
}
