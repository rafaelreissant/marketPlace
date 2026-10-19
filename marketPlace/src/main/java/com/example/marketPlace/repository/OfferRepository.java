package com.example.marketPlace.repository;

import com.example.marketPlace.model.Offer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface OfferRepository extends JpaRepository<Offer, UUID> {

    List<Offer> getAllOfferByUserId(UUID userId);

    Optional<Offer> findByUserIdAndProductId(UUID userId, UUID productId);

    Optional<Offer> findByIdAndUserId(UUID offerId, UUID userId);

    List<Offer> findAllByProductUserId(UUID userId);

    Optional<Offer> findByIdAndProductUserId(UUID offerId, UUID userId);
}