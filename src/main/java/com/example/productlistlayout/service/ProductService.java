package com.example.productlistlayout.service;

import com.example.productlistlayout.entity.Category;
import com.example.productlistlayout.entity.Product;
import com.example.productlistlayout.optionModel.IdStringModel;
import com.example.productlistlayout.repository.CategoryRepository;
import com.example.productlistlayout.repository.ProductRepository;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
@Getter
public class ProductService {
    private final
    ProductRepository productRepository;

    private final
    CategoryRepository categoryRepository;

    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    public void seed(){
        Random random = new Random();
        Category category = Category.builder()
                                .name("Fruit")
                                .build();
        categoryRepository.save(category);
        for (String fruit: new String[]{"apple", "banana", "grapes"}) {
            productRepository.save(Product.builder()
                    .name(fruit)
                    .weight(random.nextFloat(20))
                    .price(random.nextFloat(100))
                    .category(category)
                    .build()
            );
        }


    }

    private boolean isEmpty() {
        return productRepository.count() == 0;
    }

    public List<Product> getAllProduct() {
        return productRepository.findAll();
    }

    public void addProduct(Product product) {
        productRepository.save(product);
    }
    public Product getProductById(long id) {
        var value = productRepository.findById(id);
        return value.orElse(null);
    }

    public List<Product> getAllProductByIds(long[] ids) {
        List<Product> products = new ArrayList<>();
        for (long id: ids) {
            var value = productRepository.findById(id);
            value.ifPresent(products::add);
        }
        return products;
    }
    public Product getProduct(Product product){
        return getProductById(product.getId());
    }
    public void updateProduct(Product product) {
        productRepository.save(product);
    }
    public void deleteProduct(Product product) {
        productRepository.deleteById(product.getId());
    }
    public void deleteProductById(long id) {
        productRepository.deleteById(id);
    }

    public Category getCategoryById(long id) {
        return categoryRepository.findById(id).orElse(null);
    }

    public void updateCategory(Category category) {
        categoryRepository.save(category);
    }

    public List<IdStringModel> getCategoryOptions() {
        List<IdStringModel> result = new ArrayList<>();
        result.add(new IdStringModel(0,"---"));
        List<Category> categoryList=categoryRepository.findAll();
        for (Category category:
             categoryList) {
            result.add(new IdStringModel(category.getId(), category.getName()));
        }
        return result;
    }
}
