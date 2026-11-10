package com.example.marketPlace.service.specification;

import com.example.marketPlace.model.Product;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;

public class ProductSpecification {

    public static Specification<Product> hasName(String name){
        return (root, query, cb) ->
                cb.like(cb.lower(root.get("name")),"%" + name.toLowerCase() + "%");
    }

    public static Specification<Product> priceGreaterThanEqualTo(BigDecimal minPrice){
        return (root, query, cb) ->
                cb.greaterThanOrEqualTo(root.get("price"), minPrice);
    }

    public static Specification<Product> priceLessThanOrEqualTo(BigDecimal maxPrice){
        return (root, query, cb) ->
        cb.lessThanOrEqualTo(root.get("price"), maxPrice);
    }
}