package com.example.marketPlace.model;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertTrue;

class ProductTest {

    private Validator validator;

    @BeforeEach
    void setUp(){
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();

        validator = validatorFactory.getValidator();
    }

    @Test
    void shouldNotSaveWhithoutAProductName(){
        User user = new User();
        user.setName("test");
        user.setPassword("123");
        user.setEmail("test@gmail.com");

        Product product = new Product();
        product.setName("");
        product.setDescription("Surprise");
        product.setPrice(BigDecimal.valueOf(1000));
        product.setUser(user);

        Set<ConstraintViolation<Product>> violations = validator.validate(product);

        assertTrue(violations.stream()
                .anyMatch(
                        violation ->
                                violation.getPropertyPath().toString().equals("name")));
    }

    @Test
    void shouldNotSaveWhithoutAProductDescription(){
        User user = new User();
        user.setName("test");
        user.setPassword("123");
        user.setEmail("test@gmail.com");

        Product product = new Product();
        product.setName("Pencil");
        product.setDescription("");
        product.setPrice(BigDecimal.valueOf(1000));
        product.setUser(user);

        Set<ConstraintViolation<Product>> violations = validator.validate(product);

        assertTrue(violations.stream()
                .anyMatch(
                        violation ->
                                violation.getPropertyPath().toString().equals("description")));
    }

    @Test
    void shouldNotSaveWhithoutAProductPriceNull(){
        User user = new User();
        user.setName("test");
        user.setPassword("123");
        user.setEmail("test@gmail.com");

        Product product = new Product();
        product.setName("Laptop");
        product.setDescription("Surprise");
        product.setPrice(null);
        product.setUser(user);

        Set<ConstraintViolation<Product>> violations = validator.validate(product);

        assertTrue(violations.stream().
                anyMatch(
                        violation ->
                                violation.getPropertyPath().toString().equals("price")));
    }

    @Test
    void shouldNotSaveWhithoutAProductPriceLessThanZero(){
        User user = new User();
        user.setName("test");
        user.setPassword("123");
        user.setEmail("test@gmail.com");

        Product product = new Product();
        product.setName("Laptop");
        product.setDescription("Surprise");
        product.setPrice(BigDecimal.valueOf(-1000));
        product.setUser(user);

        Set<ConstraintViolation<Product>> violations = validator.validate(product);

        assertTrue(violations.stream()
                .anyMatch(
                        violation ->
                                violation.getPropertyPath().toString().equals("price")));
    }
}