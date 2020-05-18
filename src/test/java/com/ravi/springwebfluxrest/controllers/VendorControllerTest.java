package com.ravi.springwebfluxrest.controllers;

import com.ravi.springwebfluxrest.domains.Vendor;
import com.ravi.springwebfluxrest.repositories.VendorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.mockito.ArgumentMatchers.anyString;

class VendorControllerTest {

    @Mock
    VendorRepository vendorRepository;

    @InjectMocks
    VendorController vendorController;

    WebTestClient webTestClient;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        webTestClient = WebTestClient.bindToController(vendorController).build();
    }

    @Test
    void getVendors() {
        BDDMockito.given(vendorRepository.findAll())
                    .willReturn(Flux.just(Vendor.builder().firstname("ven1").lastname("venlast1").build(),
                                    Vendor.builder().firstname("ven2").lastname("venlast2").build()));

        webTestClient.get().uri(VendorController.BASE_URL)
                .exchange()
                .expectBodyList(Vendor.class)
                .hasSize(2);
    }

    @Test
    void getVendorById() {
        BDDMockito.given(vendorRepository.findById(anyString()))
                .willReturn(Mono.just(Vendor.builder().firstname("venFirst").lastname("venLast").build()));

        webTestClient.get().uri(VendorController.BASE_URL + "/test")
                .exchange()
                .expectBody(Vendor.class);
    }
}