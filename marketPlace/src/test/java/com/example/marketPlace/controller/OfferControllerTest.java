package com.example.marketPlace.controller;

import com.example.marketPlace.controller.DTO.OfferDTO;
import com.example.marketPlace.controller.mapper.OfferMapper;
import com.example.marketPlace.model.Offer;
import com.example.marketPlace.service.OfferService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(OfferController.class)
@AutoConfigureMockMvc(addFilters = false)
class OfferControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private OfferService offerService;

    @MockitoBean
    private OfferMapper offerMapper;

    @Test
    void makeOffer() throws Exception {
        UUID productId = UUID.randomUUID();

        OfferDTO offerDTO = new OfferDTO(new BigDecimal("200.00"));
        Offer offer = new Offer();
        offer.setOfferedPrice(new BigDecimal("200.00"));

        when(offerMapper.toOffer(any(OfferDTO.class)))
                .thenReturn(offer);

        mockMvc.perform(post("/offer/product/{productId}", productId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                            {
                                "offeredPrice": 200.00
                            }
                            """))
                .andExpect(status().isCreated());

        verify(offerMapper).toOffer(any(OfferDTO.class));
        verify(offerService).makeOffer(offer,productId);
    }
}