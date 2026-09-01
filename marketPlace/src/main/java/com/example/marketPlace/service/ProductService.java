package com.example.marketPlace.service;

import com.example.marketPlace.model.Product;
import com.example.marketPlace.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void createProduct(Product product){
        productRepository.save(product);
    }

    public Product getProductById(UUID uuid){
        return productRepository.findById(uuid)
                .orElseThrow(() -> new RuntimeException("product not found"));
    }

    public List<Product> getAllProduct(){
        return productRepository.findAll();
    }

}
