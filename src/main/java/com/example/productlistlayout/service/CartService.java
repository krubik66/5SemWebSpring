package com.example.productlistlayout.service;

import com.example.productlistlayout.entity.Cart;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

@Service
public class CartService {
    public Cart getCartFromCookies(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies==null) {
         return new Cart();
        }
        for (Cookie cookie: cookies) {
            if (Objects.equals(cookie.getName(), "cart")) {
                String value = cookie.getValue();
                return Cart.fromCookieString(value);
            }
        }
        return new Cart();
    }

    public void saveCartToCookies(Cart cart, HttpServletResponse response) {
        String cookieString = cart.toCookieString();
        Cookie cookie = new Cookie("cart", cookieString);
        cookie.setMaxAge(1000);
        cookie.setPath("/shop");
        response.addCookie(cookie);
    }
}
