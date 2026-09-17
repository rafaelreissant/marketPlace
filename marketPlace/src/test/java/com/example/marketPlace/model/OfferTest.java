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

class OfferTest {

    private Validator validator;

    @BeforeEach
    void setUp(){
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();

        validator = validatorFactory.getValidator();
    }

    @Test
    void shouldMakeOfferWhithPosiviteValue(){
        User user = new User();
        user.setName("test");
        user.setPassword("123");
        user.setEmail("test@gmail.com");

        Product product = new Product();
        product.setName("Laptop");
        product.setDescription("Surprise");
        product.setPrice(BigDecimal.valueOf(1000));
        product.setUser(user);

        Offer offer = new Offer();
        offer.setOfferedPrice(BigDecimal.valueOf(1000));
        offer.setUser(user);
        offer.setProduct(product);

        Set<ConstraintViolation<Offer>> violations = validator.validate(offer);

        assertTrue(violations.isEmpty());
    }

    @Test
    void shouldNotMakeOfferWhithNegativeValue() {
        User user = new User();
        user.setName("test");
        user.setPassword("123");
        user.setEmail("test@gmail.com");

        Product product = new Product();
        product.setName("Laptop");
        product.setDescription("Surprise");
        product.setPrice(BigDecimal.valueOf(1000));
        product.setUser(user);

        Offer offer = new Offer();
        offer.setOfferedPrice(BigDecimal.valueOf(-1000));
        offer.setUser(user);
        offer.setProduct(product);

        Set<ConstraintViolation<Offer>> violations = validator.validate(offer);

        assertTrue(violations.stream()
                .anyMatch(
                        violation ->
                                violation.getPropertyPath().toString().equals("offeredPrice")));
    }

    @Test
    void shouldNotMakeOfferWhithNullValue() {
        User user = new User();
        user.setName("test");
        user.setPassword("123");
        user.setEmail("test@gmail.com");

        Product product = new Product();
        product.setName("Laptop");
        product.setDescription("Surprise");
        product.setPrice(BigDecimal.valueOf(1000));
        product.setUser(user);

        Offer offer = new Offer();
        offer.setOfferedPrice(null);
        offer.setUser(user);
        offer.setProduct(product);

        Set<ConstraintViolation<Offer>> violations = validator.validate(offer);

        assertTrue(violations.stream()
                .anyMatch(
                        violation ->
                                violation.getPropertyPath().toString().equals("offeredPrice")));
    }
}