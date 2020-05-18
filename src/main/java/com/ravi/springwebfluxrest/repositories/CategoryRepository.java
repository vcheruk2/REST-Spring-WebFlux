package com.ravi.springwebfluxrest.repositories;

import com.ravi.springwebfluxrest.domains.Category;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

/* Created by: Venkata Ravichandra Cherukuri
   Created on: 5/17/2020 */
public interface CategoryRepository extends ReactiveMongoRepository<Category, String> {
}
