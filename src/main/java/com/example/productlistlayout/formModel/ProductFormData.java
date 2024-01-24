package com.example.productlistlayout.formModel;

import com.example.productlistlayout.entity.Product;
import lombok.Getter;
import lombok.Setter;

import jakarta.validation.constraints.DecimalMin;

@Getter
@Setter
public class ProductFormData {
    long productId;
    String name;
    private Long categoryId;
    @DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than 0")
    Float price;
    @DecimalMin(value = "0.0", inclusive = false, message = "Weight must be greater than 0")
    Float weight;

    public static ProductFormData fromProduct(Product product){
        ProductFormData result = new ProductFormData();
        result.productId=product.getId();
        result.name= product.getName();
        result.price= product.getPrice();
        result.weight= product.getWeight();
        if(product.getCategory()==null) {
            result.categoryId=0L;
        }
        else {
            result.categoryId=product.getCategory().getId();
        }
        return result;
    }

    public void updateProduct(Product product) {
       product.setName(this.name);
       product.setWeight(this.weight);
       product.setPrice(this.price);
    }

}
