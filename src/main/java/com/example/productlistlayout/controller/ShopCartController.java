package com.example.productlistlayout.controller;

import com.example.productlistlayout.entity.Cart;
import com.example.productlistlayout.entity.Product;
import com.example.productlistlayout.service.CartService;
import com.example.productlistlayout.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

@Controller
@RequestMapping("/shop")
public class ShopCartController {

    private final CartService cartService;

    private final ProductService productService;

    public ShopCartController(CartService cartService, ProductService productService) {
        this.cartService = cartService;
        this.productService = productService;
    }

    @GetMapping({"/", "", "/index"})
    public String home(Locale locale, Model model) {
        List<Product> productList = productService.getAllProduct();
        model.addAttribute("productList", productList);
        return "shop/index";
    }

    @PostMapping("/add/{id}")
    public String addToCart(@PathVariable long id, HttpServletResponse response, HttpServletRequest request) {
        Cart cart = cartService.getCartFromCookies(request);
        cart.addItem(id);
        cartService.saveCartToCookies(cart, response);
        return "redirect:/shop";
    }

    @GetMapping("/plus/{id}")
    public String plus(@PathVariable long id, HttpServletResponse response, HttpServletRequest request) {
        Cart cart = cartService.getCartFromCookies(request);
        cart.addItem(id);
        cartService.saveCartToCookies(cart, response);
        return "shop/cart";
    }

    @GetMapping("/minus/{id}")
    public String minus(@PathVariable long id, HttpServletResponse response, HttpServletRequest request) {
        Cart cart = cartService.getCartFromCookies(request);
        cart.reduceItem(id);
        cartService.saveCartToCookies(cart, response);
        return "shop/cart";
    }

    @GetMapping("/remove/{id}")
    public String remove(@PathVariable long id, HttpServletResponse response, HttpServletRequest request) {
        Cart cart = cartService.getCartFromCookies(request);
        cart.removeItem(id);
        cartService.saveCartToCookies(cart, response);
        return "shop/cart";
    }

    @GetMapping("/cart")
    public String showCart(HttpServletRequest request, Model model) {
        Cart cart = cartService.getCartFromCookies(request);
        List<Product> products = new ArrayList<>();
        cart.getItems().forEach((k,v) -> products.add(productService.getProductById(k.amount)));

        model.addAttribute("products",products);
        model.addAttribute("cart",cart);
        return "shop/cart";
    }
}
