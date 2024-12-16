package com.example.book.controllers;

import com.example.book.models.CartDetail;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Repository
@CrossOrigin("*")
@RequestMapping("/cartdDetial")
public class CartDetailReponsitory {
    private final CartDetailReponsitory cartDetailReponsitory;

    public CartDetailReponsitory(CartDetailReponsitory cartDetailReponsitory) {
        this.cartDetailReponsitory = cartDetailReponsitory;
    }
    @GetMapping("/get")
    public List<CartDetail> findAll() {
        return cartDetailReponsitory.findAll();
    }

    public CartDetail findById(String id) {
        return cartDetailReponsitory.findById(id);
    }
}
