package com.example.marketPlace.controller.mapper;

import com.example.marketPlace.controller.DTO.OfferDTO;
import com.example.marketPlace.model.Offer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OfferMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "product", ignore = true)
    Offer toOffer(OfferDTO offerDTO);

    OfferDTO toDto(Offer offer);
}
