package com.example.marketPlace.service;

import com.example.marketPlace.model.Product;
import com.example.marketPlace.model.User;
import com.example.marketPlace.repository.ProductRepository;
import com.example.marketPlace.service.authencitcate.AuthenticatedUserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final AuthenticatedUserService authenticatedUserService;

    public ProductService(ProductRepository productRepository, AuthenticatedUserService authenticatedUserService) {
        this.productRepository = productRepository;
        this.authenticatedUserService = authenticatedUserService;
    }

    public void createProduct(Product product){

        User user = authenticatedUserService.getUserAuthenticated();

        product.setUser(user);

        productRepository.save(product);
    }

    public Product getProductById(UUID productId){

        return productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("product not found"));
    }

    public Product getMyProductById(UUID uuid){

        User user = authenticatedUserService.getUserAuthenticated();

        return productRepository.findByIdAndUserId(uuid, user.getId())
                .orElseThrow(() -> new RuntimeException("product not found"));
    }

    public List<Product> getAllProductsByUserId(){

        User user = authenticatedUserService.getUserAuthenticated();

        return productRepository.findAllProductByUserId(user.getId());
    }

    public List<Product> getAllProduct(){
        return productRepository.findAll();
    }

}
