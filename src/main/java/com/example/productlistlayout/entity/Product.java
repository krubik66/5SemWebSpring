package com.example.productlistlayout.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CUST_SEQ")
    private long id;
    private String name;
    @ManyToOne
    @JoinTable (name="productsCategory")
    private Category category;
    @DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than 0")
    private Float price;
    @DecimalMin(value = "0.0", inclusive = false, message = "Weight must be greater than 0")
    private Float weight;
}
