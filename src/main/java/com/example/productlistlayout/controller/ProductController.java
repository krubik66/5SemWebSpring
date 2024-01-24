package com.example.productlistlayout.controller;

import com.example.productlistlayout.entity.Category;
import com.example.productlistlayout.formModel.ProductFormData;
import com.example.productlistlayout.service.ProductService;
import com.example.productlistlayout.entity.Product;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Controller
@RequestMapping("/product")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping({"/", "", "/index"})
    public String home(Locale locale, Model model) {
        Date date = new Date();
        DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG,
                DateFormat.LONG, locale);
        String serverTime = dateFormat.format(date);
        model.addAttribute("serverTime", serverTime.toString() );
        List<Product> productList = productService.getAllProduct();
        model.addAttribute("productList", productList );
        List<Category> categoryList = productService.getCategoryRepository().findAll();
        model.addAttribute("categoryList", categoryList);
        return "product/index";
    }

    @GetMapping("/seed")
    public String seed() {
        productService.seed();
        return "redirect:/product/";
    }


    @GetMapping("/add")
    public String add(Model model) {
        model.addAttribute("productFormData",ProductFormData.fromProduct(new Product()));
        model.addAttribute("categoryOptions", productService.getCategoryOptions());
        return "product/add";
    }

    @PostMapping("/add")
    public String add(@Valid @ModelAttribute ProductFormData productFormData, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/product/add";
        }
        Product product = new Product();
        productFormData.updateProduct(product);
        Category category = productService.getCategoryById(productFormData.getCategoryId());
        product.setCategory(category);
        productService.addProduct(product);
        return "redirect:/product/";
    }


    // how to put a parameter in a query string
    // e.a. /product/details?id=3
    @GetMapping("/details")
    public String add(@RequestParam("id") long inputId, Model model) {
        model.addAttribute("product", productService.getProductById(inputId) );
        return "/product/details";
    }

    // how to put parameter in a URL
    // e.a. /product/3/edit
    @GetMapping(value = {"/{prodId}/edit"})
    public String edit(Model model, @PathVariable Integer prodId) {
        model.addAttribute("productFormData", ProductFormData.fromProduct(productService.getProductById(prodId)) );
        model.addAttribute("categoryOptions", productService.getCategoryOptions());
        return "/product/edit";
    }

    @PostMapping(value = {"/edit"})
    public String edit(@Valid @ModelAttribute ProductFormData productFormData, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/product/edit";
        }
        Product product = productService.getProductById(productFormData.getProductId());
        Category category = productService.getCategoryById(productFormData.getCategoryId());
        product.setCategory(category);
        productService.updateProduct(product);
        return "redirect:/product/";
    }

    // how to put a parameter in a query string
    // e.a. /product/remove?id=3
    @GetMapping("/remove")
    public String remove(@RequestParam("id") long id) {
        productService.deleteProductById(id);
        return "redirect:/product/";
    }

}
