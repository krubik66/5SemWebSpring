package com.example.productlistlayout.entity;

import lombok.AllArgsConstructor;

import java.util.*;

public class Cart {

    @AllArgsConstructor
    public class Amount {
        public long amount;

        @Override
        public String toString() {
            return String.valueOf(amount);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Amount)) return false;
            Amount amount1 = (Amount) o;
            return amount == amount1.amount;
        }

        @Override
        public int hashCode() {
            return Objects.hash(amount);
        }
    }

    public Map<Amount,Amount> productList;

    public Cart() {
        productList = new HashMap<>() {
        };
    }

    public void addItem(long l) {
        Amount id = new Amount(l);
        if (productList.containsKey(id)) {
            productList.get(id).amount++;
        } else {
            productList.put(id,new Amount(1));
        }
    }

    public void reduceItem(long l) {
        Amount id = new Amount(l);
        if (productList.get(id).amount==1) {
            productList.remove(id);
        } else {
            productList.get(id).amount--;
        }
    }

    public void removeItem(long l) {
        Amount id = new Amount(l);
        productList.remove(id);
    }

    public Map<Amount,Amount> getItems() {
        return productList;
    }

    public long[] getIds() {
        return productList.keySet().stream().mapToLong(k -> k.amount).toArray();
    }

    public String toCookieString(){
        StringBuilder result = new StringBuilder();
        productList.forEach((k,v) -> result.append(k).append("i").append(v).append("I"));
        return result.toString();
    }

    private Amount amount(String s) {
        return new Amount(Long.parseLong(s));
    }

    public static Cart fromCookieString(String cookieString){
        Cart cart = new Cart();
        String[] entries = cookieString.split("I");
        for (String s: entries) {
            String[] kv = s.split("i");
            cart.productList.put(cart.amount(kv[0]), cart.amount(kv[1]));
        }
        return cart;
    }
}
