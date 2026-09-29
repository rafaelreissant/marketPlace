package com.example.marketPlace.controller.mapper;

import com.example.marketPlace.controller.DTO.ProductDTO;
import com.example.marketPlace.model.Product;
import com.example.marketPlace.model.User;
import java.math.BigDecimal;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-09-29T11:00:59-0300",
    comments = "version: 1.6.3, compiler: javac, environment: Java 25.0.1 (Microsoft)"
)
@Component
public class ProductMapperImpl implements ProductMapper {

    @Override
    public Product toProduct(ProductDTO productDTO) {
        if ( productDTO == null ) {
            return null;
        }

        Product product = new Product();

        product.setName( productDTO.name() );
        product.setDescription( productDTO.description() );
        product.setPrice( productDTO.price() );
        product.setUser( productDTO.user() );

        return product;
    }

    @Override
    public ProductDTO toDto(Product product) {
        if ( product == null ) {
            return null;
        }

        String name = null;
        String description = null;
        BigDecimal price = null;
        User user = null;

        name = product.getName();
        description = product.getDescription();
        price = product.getPrice();
        user = product.getUser();

        ProductDTO productDTO = new ProductDTO( name, description, price, user );

        return productDTO;
    }
}
