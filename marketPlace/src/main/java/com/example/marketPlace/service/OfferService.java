package com.example.marketPlace.service;

import com.example.marketPlace.model.Offer;
import com.example.marketPlace.repository.OfferRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class OfferService {

    private final OfferRepository offerRepository;

    public OfferService(OfferRepository offerRepository) {
        this.offerRepository = offerRepository;
    }

    public void makeOffer(Offer offer){

        Optional<Offer> existingOffer =
                offerRepository.findByUserIdAndProductId(
                        offer.getUser().getId(),
                        offer.getProduct().getId()
                );

        if (existingOffer.isPresent()){
            throw  new RuntimeException("Offer already exist");
        }

        offerRepository.save(offer);
    }

    public Offer getOfferById(UUID uuid){
        return offerRepository.findById(uuid)
                .orElseThrow(() -> new RuntimeException(" offer not found"));
    }

    public List<Offer> getAllOffer(){
        return offerRepository.findAll();
    }
}
