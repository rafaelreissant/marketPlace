package com.example.marketPlace.service;

import com.example.marketPlace.model.Offer;
import com.example.marketPlace.model.Product;
import com.example.marketPlace.model.User;
import com.example.marketPlace.repository.OfferRepository;
import com.example.marketPlace.repository.ProductRepository;
import com.example.marketPlace.service.authencitcate.AuthenticatedUserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class OfferService {

    private final OfferRepository offerRepository;
    private final AuthenticatedUserService authenticatedUserService;
    private final ProductRepository productRepository;

    public OfferService(OfferRepository offerRepository, AuthenticatedUserService authenticatedUserService, ProductRepository productRepository) {
        this.offerRepository = offerRepository;
        this.authenticatedUserService = authenticatedUserService;
        this.productRepository = productRepository;
    }

    public void makeOffer(Offer offer, UUID productId){

        User user = authenticatedUserService.getUserAuthenticated();

        Product product = productRepository.findById(productId)
                .orElseThrow(() ->
                        new RuntimeException("Product not found"));

        if (product.getUser().getId().equals(user.getId())) {
            throw new RuntimeException(
                    "You cannot make an offer on your own product");
        }

        Optional<Offer> existingOffer =
                offerRepository.findByUserIdAndProductId(
                        user.getId(),
                        product.getId());

        if (existingOffer.isPresent()) {
            throw new RuntimeException("User already made an offer for this product");
        }

        offer.setUser(user);
        offer.setProduct(product);

        offerRepository.save(offer);
    }

    public Offer getOfferMadeById(UUID offerId){

        User user = authenticatedUserService.getUserAuthenticated();

        return offerRepository
                .findByIdAndUserId(offerId, user.getId())
                .orElseThrow(() ->
                        new RuntimeException(" offer not found"));
    }

    public Offer getOfferReceivedById(UUID offerId) {

        User user = authenticatedUserService.getUserAuthenticated();

        return offerRepository
                .findByIdAndProductUserId(offerId, user.getId())
                .orElseThrow(() ->
                        new RuntimeException("Offer not found"));
    }

    public List<Offer> getAllOffer(){
        return offerRepository.findAll();
    }

    public List<Offer> getAllOfferMadeByUser(){

        User user = authenticatedUserService.getUserAuthenticated();

        return offerRepository.getAllOfferByUserId(user.getId());
    }

    public List<Offer> getAllOffersReceivedByUser() {

        User user = authenticatedUserService.getUserAuthenticated();

        return offerRepository.findAllByProductUserId(user.getId());
    }
}