package com.example.marketPlace.controller;

import com.example.marketPlace.controller.DTO.OfferDTO;
import com.example.marketPlace.controller.mapper.OfferMapper;
import com.example.marketPlace.model.Offer;
import com.example.marketPlace.service.OfferService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("offer")
public class OfferController {

    private final OfferService offerService;
    private final OfferMapper offerMapper;

    public OfferController(OfferService offerService, OfferMapper offerMapper) {
        this.offerService = offerService;
        this.offerMapper = offerMapper;
    }

    @PostMapping("/product/{productId}")
    public ResponseEntity<Void> makeOffer(
            @PathVariable UUID productId,
            @Valid @RequestBody OfferDTO offerDTO){

        Offer offer = offerMapper.toOffer(offerDTO);

        offerService.makeOffer(offer, productId);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/made")
    public ResponseEntity<List<OfferDTO>> getAllOffersMadeByUser(){
        List offers = offerService.getAllOfferMadeByUser()
                .stream().map(offerMapper::toDto).toList();

        return ResponseEntity.ok(offers);
    }

    @GetMapping("/received")
    public ResponseEntity<List<OfferDTO>> getAllOffersReceivedByUser(){
        List offers = offerService.getAllOffersReceivedByUser()
                .stream().map(offerMapper::toDto).toList();

        return ResponseEntity.ok(offers);
    }

    @GetMapping("/made/{id}")
    public ResponseEntity<OfferDTO> getOfferMadeById(@PathVariable("id") UUID uuid){
        Offer offer = offerService.getOfferMadeById(uuid);

        return ResponseEntity.ok(offerMapper.toDto(offer));
    }

    @GetMapping("/received/{id}")
    public ResponseEntity<OfferDTO> getOfferReceivedById(@PathVariable("id") UUID uuid){
        Offer offer = offerService.getOfferReceivedById(uuid);

        return ResponseEntity.ok(offerMapper.toDto(offer));
    }
}