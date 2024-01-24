package com.example.productlistlayout.controller;

import com.example.productlistlayout.entity.Category;
import com.example.productlistlayout.entity.Product;
import com.example.productlistlayout.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/category")
public class CategoryController {
    private final ProductService productService;

    public CategoryController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/add")
    public String add(Model model) {
        model.addAttribute("category", new Category());
        return "category/add";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute Category category) {
        productService.getCategoryRepository().save(category);
        return "redirect:/product/";
    }

    @GetMapping(value = {"/{categoryId}/edit"})
    public String edit(Model model, @PathVariable Integer categoryId) {
        model.addAttribute("category", productService.getCategoryById(categoryId));
        return "/category/edit";
    }

    @PostMapping(value = {"/edit"})
    public String edit(@ModelAttribute Category category) {
        productService.updateCategory(category);
        return "redirect:/product/";
    }

    @GetMapping(value = {"/remove"})
    public String remove(@RequestParam("id") long id) {
        List<Product> list = productService.getProductRepository().findAll();
        list.removeIf(a -> a.getCategory() == null || a.getCategory().getId() != id);
        for (Product product:
             list) {
            productService.deleteProductById(product.getId());
        }
        productService.getCategoryRepository().deleteById(id);
        return "redirect:/product/";
    }
}
