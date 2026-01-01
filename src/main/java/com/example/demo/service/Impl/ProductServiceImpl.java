package com.example.demo.service.Impl;

import com.example.demo.model.Product;
import com.example.demo.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class ProductServiceImpl implements ProductService {

    private static final HashMap<String, Product> productRepo = new HashMap<>();
    static {
        Product honey = new Product();
        honey.setId("1");
        honey.setName("Honey");
        productRepo.put(honey.getId(), honey);

        Product almond = new Product();
        almond.setId("2");
        almond.setName("Almond");
        productRepo.put(almond.getId(), almond);
    }

    @Override
    public Iterable<Product> productList() {
        return productRepo.values();
    }

    @Override
    public Iterable<Product> productCreate(String id, String name) {
        Product herrylo = new Product();
        herrylo.setId(id);
        herrylo.setName(name);
        productRepo.put(herrylo.getId(), herrylo);
        return productRepo.values();
    }
}
