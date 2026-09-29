package com.example.marketPlace.controller;

import com.example.marketPlace.controller.DTO.ProductDTO;
import com.example.marketPlace.controller.mapper.ProductMapper;
import com.example.marketPlace.model.Product;
import com.example.marketPlace.service.ProductService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("product")
public class ProductController {

    private final ProductService productService;
    private final ProductMapper productMapper;

    public ProductController(ProductService productService, ProductMapper productMapper) {
        this.productService = productService;
        this.productMapper = productMapper;
    }

    @PostMapping
    public ResponseEntity<Void> createProduct(@Valid @RequestBody ProductDTO productDTO){
        Product product = productMapper.toProduct(productDTO);
        productService.createProduct(product);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/id").buildAndExpand(product.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable("id") UUID id){
        Product product = productService.getProductById(id);

        if (product.getId() == null){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(productMapper.toDto(product));
    }

    @GetMapping
    public ResponseEntity<List<ProductDTO>> getAllProducts(){
        List<ProductDTO> productDTOList = productService.getAllProduct()
                .stream().map(productMapper::toDto).toList();

        if (productDTOList.isEmpty()){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(productDTOList);
    }
}