package com.example.marketPlace.controller;

import com.example.marketPlace.controller.DTO.ProductDTO;
import com.example.marketPlace.controller.mapper.ProductMapper;
import com.example.marketPlace.model.Product;
import com.example.marketPlace.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(ProductController.class)
@AutoConfigureMockMvc(addFilters = false)
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ProductService productService;

    @MockitoBean
    private ProductMapper productMapper;

    @Test
    void getAllProduct() throws Exception {
        Product product = new Product();
        product.setId(UUID.randomUUID());
        product.setName("Notebook");
        product.setDescription("Notebook usado");
        product.setPrice(new BigDecimal("2500.00"));

        ProductDTO productDTO = new ProductDTO(
                "Notebook",
                "Notebook usado",
                new BigDecimal("2500.00")
        );

        when(productService.getAllProduct()).thenReturn(List.of(product));

        when(productMapper.toDto(product)).thenReturn(productDTO);

        mockMvc.perform(get("/product"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Notebook"))
                .andExpect(jsonPath("$[0].description").value("Notebook usado"))
                .andExpect(jsonPath("$[0].price").value(2500.00));
    }

    @Test
    void getListWithNoContent() throws Exception {
        when(productService.getAllProduct()).thenReturn(List.of());

        mockMvc.perform(get("/product")).andDo(print()).andExpect(status().isNoContent());
    }
}