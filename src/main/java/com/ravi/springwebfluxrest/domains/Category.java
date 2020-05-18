package com.ravi.springwebfluxrest.domains;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/* Created by: Venkata Ravichandra Cherukuri
   Created on: 5/17/2020 */
@Document
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Category {

    @Id
    private String id;

    private String description;
}
