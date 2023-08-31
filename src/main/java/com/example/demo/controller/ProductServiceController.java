package com.example.demo.controller;

import com.example.demo.model.Product;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
public class ProductServiceController  {
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

    @GetMapping(value = "/productList")
    public ResponseEntity<Object> getProduct() {
        return new ResponseEntity<>(productRepo.values(), HttpStatus.OK);
    }

    @RequestMapping(value = "/product", method = RequestMethod.POST)
    public ResponseEntity<Object> createProduct(@RequestParam String id, @RequestParam String name) {
        Product herrylo = new Product();
        herrylo.setId(id);
        herrylo.setName(name);
        productRepo.put(herrylo.getId(), herrylo);
        return new ResponseEntity<>(productRepo.values(), HttpStatus.OK);
    }
}
