package com.example.marketPlace.repository;

import com.example.marketPlace.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {

    List<Product> findAllProductByUserId(UUID userId);

    Optional<Product> findByIdAndUserId(UUID productId, UUID UserId);
}