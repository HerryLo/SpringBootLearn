package com.example.demo.service;

import com.example.demo.model.Product;

public interface ProductService {
    Iterable<Product> productList();
    Iterable<Product> productCreate(String id, String name);
}
