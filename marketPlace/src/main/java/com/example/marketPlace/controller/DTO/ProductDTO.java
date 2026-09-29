package com.example.marketPlace.controller.DTO;

import com.example.marketPlace.model.User;

import java.math.BigDecimal;

public record ProductDTO(String name,
                         String description,
                         BigDecimal price,
                         User user) {
}
