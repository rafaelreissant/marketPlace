package com.example.marketPlace.repository;

import com.example.marketPlace.model.Product;
import com.example.marketPlace.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@DataJpaTest
class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    void creatProduct(){
        User user = new User();
        user.setName("test");
        user.setPassword("123");
        user.setEmail("test@gmail.com");

        userRepository.save(user);

        Product product = new Product();
        product.setName("Pencil");
        product.setDescription("sucesso");
        product.setPrice(BigDecimal.valueOf(1000));
        product.setUser(user);

        productRepository.save(product);

        assertNotNull(product.getId());
    }

    @Test
    void getProductById(){
        User user = new User();
        user.setName("test");
        user.setPassword("123");
        user.setEmail("test@gmail.com");

        userRepository.save(user);

        Product product = new Product();
        product.setName("Pencil");
        product.setDescription("sucesso");
        product.setPrice(BigDecimal.valueOf(1000));
        product.setUser(user);

        Product product1 = productRepository.save(product);
        Product product2 = productRepository.findById(product1.getId()).orElseThrow();

        assertEquals(product1.getId(), product2.getId());
    }

    @Test
    void getAllProducts(){
        User user = new User();
        user.setName("test");
        user.setPassword("123");
        user.setEmail("test@gmail.com");

        userRepository.save(user);

        Product product1 = new Product();
        product1.setName("Pencil");
        product1.setDescription("sucesso");
        product1.setPrice(BigDecimal.valueOf(1000));
        product1.setUser(user);

        productRepository.save(product1);

        Product product2 = new Product();
        product2.setName("Tv smart");
        product2.setDescription("velho");
        product2.setPrice(BigDecimal.valueOf(350));
        product2.setUser(user);

        productRepository.save(product2);

        List<Product> productList = productRepository.findAll();

        assertNotNull(productList);
        assertEquals(2, productList.size());
    }
}