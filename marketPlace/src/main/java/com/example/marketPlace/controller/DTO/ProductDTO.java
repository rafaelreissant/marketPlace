package com.example.marketPlace.controller.DTO;

import com.example.marketPlace.model.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record ProductDTO(
        @NotBlank
        String name,

        @NotBlank
        String description,

        @NotNull
        @Positive
        BigDecimal price
) {}
