package com.ravi.springwebfluxrest.controllers;

import com.ravi.springwebfluxrest.domains.Category;
import com.ravi.springwebfluxrest.repositories.CategoryRepository;
import org.reactivestreams.Publisher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/* Created by: Venkata Ravichandra Cherukuri
   Created on: 5/17/2020 */
@RestController
@RequestMapping(CategoryController.BASE_URL)
public class CategoryController {

    private final CategoryRepository categoryRepository;
    public static final String BASE_URL = "/api/v1/categories";

    public CategoryController(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @GetMapping
    Flux<Category> getCategories(){
        return categoryRepository.findAll();
    }

    @GetMapping("/{id}")
    Mono<Category> getCategoryById(@PathVariable("id") String id){
        return categoryRepository.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    Mono<Void> createCategory(@RequestBody Publisher<Category> categoryStream){
        return categoryRepository.saveAll(categoryStream).then();
    }
}
