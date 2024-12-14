package com.example.book.controllers;

import com.example.book.models.Product;
import com.example.book.repository.ProductReponsitory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/products")
@CrossOrigin("*")
public class productController {
    private final ProductReponsitory productReponsitory;

    public productController(ProductReponsitory productReponsitory) {
        this.productReponsitory = productReponsitory;
    }

    @GetMapping("")
    List<Product> getAllProducts() {
        return productReponsitory.findAll();
    }
    @GetMapping("/{id}")
    Product getProductById(@PathVariable Integer id) {
        return productReponsitory.findById(id);
    }
}
