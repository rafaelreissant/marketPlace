package com.example.marketPlace.service;

import com.example.marketPlace.model.Product;
import com.example.marketPlace.model.User;
import com.example.marketPlace.repository.ProductRepository;
import com.example.marketPlace.repository.UserRepository;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public ProductService(ProductRepository productRepository, UserRepository userRepository) {
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    public void createProduct(Product product){

        @Nullable Authentication authentication = SecurityContextHolder
                .getContext().getAuthentication();

        String email = authentication.getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        product.setUser(user);

        productRepository.save(product);
    }

    public Product getProductById(UUID uuid){

        @Nullable Authentication authentication = SecurityContextHolder
                                                    .getContext().getAuthentication();

        String email = authentication.getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return productRepository.findByIdAndUserId(uuid, user.getId())
                .orElseThrow(() -> new RuntimeException("product not found"));
    }

    public List<Product> getAllProduct(){
        return productRepository.findAll();
    }

}
