package com.example.marketPlace.repository;

import com.example.marketPlace.model.Offer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface OfferRepository extends JpaRepository<Offer, UUID> {

    Optional<Offer> findByUserIdAndProductId(UUID userId, UUID productId);
}