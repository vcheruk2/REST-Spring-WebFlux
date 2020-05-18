package com.ravi.springwebfluxrest.controllers;

import com.ravi.springwebfluxrest.domains.Category;
import com.ravi.springwebfluxrest.repositories.CategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.reactivestreams.Publisher;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

class CategoryControllerTest {

    WebTestClient webTestClient;

    @Mock
    CategoryRepository categoryRepository;

    @InjectMocks
    CategoryController categoryController;

    @BeforeEach
    void setUp() {
        // Alternate way of doing below
        //categoryRepository = Mockito.mock(CategoryRepository.class);
        //categoryController = new CategoryController(categoryRepository);

        // Alternate way of doing this is above
        MockitoAnnotations.initMocks(this);

        webTestClient = WebTestClient.bindToController(categoryController).build();
    }

    @Test
    void getCategories() {
        // Given
        BDDMockito.given(categoryRepository.findAll())
                .willReturn(Flux.just(Category.builder().description("cat1").build(),
                        Category.builder().description("cat2").build()));

        webTestClient.get().uri(CategoryController.BASE_URL)
                    .exchange()
                    .expectBodyList(Category.class)
                    .hasSize(2);
    }

    @Test
    void getCategoryById() {
        // Given
        BDDMockito.given(categoryRepository.findById(anyString()))
                .willReturn(Mono.just(Category.builder().description("cat1").build()));

        webTestClient.get().uri(CategoryController.BASE_URL+"/some")
                .exchange()
                .expectBody(Category.class);
    }

    @Test
    void createCategory() {
        BDDMockito.given(categoryRepository.saveAll(any(Publisher.class)))
                .willReturn(Flux.just(Category.builder().build()));

        Mono<Category> categoryMono = Mono.just(Category.builder().description("cat").build());

        webTestClient.post().uri(CategoryController.BASE_URL)
                .body(categoryMono, Category.class)
                .exchange()
                .expectStatus().isCreated();
    }
}