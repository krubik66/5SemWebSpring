package com.example.productlistlayout.entity;

public enum Role {
    CUSTOMER,
    ADMIN;

    @Override
    public String toString() {
        if (this==CUSTOMER) {
            return "CUSTOMER";
        } else {
            return "ADMIN";
        }
    }
}