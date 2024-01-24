package com.example.productlistlayout.entity;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CUST_SEQ")
    private long id;

    @Size(max = 255)
    @NotBlank(message="Invalid product name!")
    private String name;
    @ManyToOne
    @JoinTable (name="productsCategory")
    private Category category;
    @DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than 0")
    private Float price;
    @DecimalMin(value = "0.0", inclusive = false, message = "Weight must be greater than 0")
    private Float weight;

}
