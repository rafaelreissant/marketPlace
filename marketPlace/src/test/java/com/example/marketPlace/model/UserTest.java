package com.example.marketPlace.model;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertFalse;

class UserTest {

   private Validator validator;

   @BeforeEach
   void setUp(){
       ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();

       validator = validatorFactory.getValidator();
   }


    @Test
    void shouldNotSaveWhithoutAName(){
        User user = new User();
        user.setName("");
        user.setPassword("123");
        user.setEmail("test@gmail.com");

        Set<ConstraintViolation<User>> violations = validator.validate(user);

        assertFalse(violations.isEmpty());

    }

    @Test
    void shouldNotSaveWhithoutAPassword(){
        User user = new User();
        user.setName("test");
        user.setPassword("");
        user.setEmail("test@gmail.com");

        Set<ConstraintViolation<User>> violations = validator.validate(user);

        assertFalse(violations.isEmpty());
    }

    @Test
    void shouldNotSaveWhithoutAEmail(){
        User user = new User();
        user.setName("test");
        user.setPassword("123");
        user.setEmail("");

        Set<ConstraintViolation<User>> violations = validator.validate(user);

        assertFalse(violations.isEmpty());
    }
}