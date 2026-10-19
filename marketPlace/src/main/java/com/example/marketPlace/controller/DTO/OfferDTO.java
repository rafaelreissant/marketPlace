package com.example.marketPlace.controller.DTO;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record OfferDTO(@NotNull
                       @Positive
                       BigDecimal offeredPrice) {
}
