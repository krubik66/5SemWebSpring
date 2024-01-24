package com.example.productlistlayout.controller;

import com.example.productlistlayout.entity.Product;
import com.example.productlistlayout.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.*;

@Controller
@RequestMapping("/shop")
public class ShopCartController {

    private final ProductService productService;

    public ShopCartController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping({"/", "", "/index"})
    public String home(Locale locale, Model model) {
        List<Product> productList = productService.getAllProduct();
        model.addAttribute("productList", productList);
        return "shop/index";
    }

    @GetMapping("/cart")
    public String showCart(Model model) {
        var products = productService.getAllProduct();

        model.addAttribute("products",products);
        return "shop/cart";
    }
}
