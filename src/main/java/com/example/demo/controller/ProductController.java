package com.example.demo.controller;

import com.example.demo.pojo.Product;
import com.example.demo.pojo.Result;
import com.example.demo.service.Impl.ProductServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api")
public class ProductController {
    ProductServiceImpl ProductService;

    // 推荐使用构造注入
    public ProductController(ProductServiceImpl ProductService) {
        this.ProductService = ProductService;
    }

    @GetMapping("/productList")
    public ResponseEntity<Object> getProduct() {
        Iterable<Product> products = ProductService.productList();
        return new ResponseEntity(Result.success(products), HttpStatus.OK);
    }

    @RequestMapping(value = "product", method = RequestMethod.POST)
    public ResponseEntity productCreate(@RequestBody Product product) {
        Iterable<Product> products = ProductService.productCreate(product.getId(), product.getName());
        return new ResponseEntity(Result.success(products), HttpStatus.OK);
    }
}
