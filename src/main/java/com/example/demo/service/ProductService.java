package com.example.demo.service;

import com.example.demo.pojo.Product;

public interface ProductService {
    Iterable<Product> productList();
    Iterable<Product> productCreate(String id, String name);
}
