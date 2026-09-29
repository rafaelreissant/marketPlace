package com.example.marketPlace.controller.mapper;

import com.example.marketPlace.controller.DTO.ProductDTO;
import com.example.marketPlace.model.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    Product toProduct(ProductDTO productDTO);

    ProductDTO toDto(Product product);
}
