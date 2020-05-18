package com.ravi.springwebfluxrest.bootstrap;

import com.ravi.springwebfluxrest.domains.Category;
import com.ravi.springwebfluxrest.domains.Vendor;
import com.ravi.springwebfluxrest.repositories.CategoryRepository;
import com.ravi.springwebfluxrest.repositories.VendorRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/* Created by: Venkata Ravichandra Cherukuri
   Created on: 5/17/2020 */
@Component
public class Bootstrap implements CommandLineRunner {

    private final CategoryRepository categoryRepository;
    private final VendorRepository vendorRepository;

    public Bootstrap(CategoryRepository categoryRepository, VendorRepository vendorRepository) {
        this.categoryRepository = categoryRepository;
        this.vendorRepository = vendorRepository;
    }

    private void loadCategories(){
        if(categoryRepository.count().block() != 0)
            return;

        System.out.println("Loading Category data");

        categoryRepository.save(Category.builder().description("Cat1").build()).block();
        categoryRepository.save(Category.builder().description("Cat2").build()).block();

        System.out.println("Loaded Category data of size " + categoryRepository.count().block());
    }

    private void loadVendors(){
        if (vendorRepository.count().block() != 0)
            return;

        System.out.println("Loading Vendor data");

        vendorRepository.save(Vendor.builder().firstname("VenFirst1").lastname("VenLast1").build()).block();
        vendorRepository.save(Vendor.builder().firstname("VenFirst2").lastname("VenLast2").build()).block();

        System.out.println("Loaded Vendor data of size " + vendorRepository.count().block());
    }

    @Override
    public void run(String... args) throws Exception {
        loadCategories();
        loadVendors();
    }
}
