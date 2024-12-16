package com.example.book.controllers;

import com.example.book.models.Cart;
import com.example.book.repository.CartReponsitory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin("*")
@RestController
@RequestMapping(path = "/cart")
public class cartController {
    private final CartReponsitory  cartReponsitory;

    public cartController(CartReponsitory cartReponsitory) {
        this.cartReponsitory = cartReponsitory;
    }

    // Lấy tất cả Cart
    @GetMapping
    public ResponseEntity<List<Cart>> getAllCarts() {
        List<Cart> carts = cartReponsitory.findAll();
        return new ResponseEntity<>(carts, HttpStatus.OK);
    }

    // Lấy Cart theo ID
    @GetMapping("{id}")
    public ResponseEntity<?> getCartById(@PathVariable Integer id) {
        Optional<Cart> cart = Optional.ofNullable(cartReponsitory.findById(id));
        if (cart.isPresent()) {
            return new ResponseEntity<>(cart.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Cart không tồn tại với ID: " + id, HttpStatus.NOT_FOUND);
        }
    }

    // Lấy Cart theo userID
    @GetMapping("/userID/{userId}")
    public ResponseEntity<?> getCartByUserId(@PathVariable Integer userId) {
        Cart cart = cartReponsitory.findByUserId(userId);
        if (cart == null) {
            // Tạo mới Cart nếu không tìm thấy
            Cart newCart = new Cart();
            newCart.setUserId(userId);
            cartReponsitory.save(newCart);
            return new ResponseEntity<>(newCart, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(cart, HttpStatus.OK);
        }
    }

    // Thêm Cart mới
//    @PostMapping
//    public ResponseEntity<?> addCart(@RequestBody Cart cart) {
//        try {
//            Cart savedCart = cartReponsitory.save(cart);
//            return new ResponseEntity<>(savedCart, HttpStatus.CREATED);
//        } catch (Exception e) {
//            return new ResponseEntity<>("Lỗi khi lưu Cart: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }

    // Xóa Cart theo ID
//    @DeleteMapping("{id}")
//    public ResponseEntity<?> deleteCart(@PathVariable Integer id) {
//        Optional<Cart> cart = Optional.ofNullable(cartReponsitory.findById(id));
//        if (cart.isPresent()) {
//            cartReponsitory.deleteById(id);
//            return new ResponseEntity<>("Đã xóa Cart với ID: " + id, HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>("Không tìm thấy Cart với ID: " + id, HttpStatus.NOT_FOUND);
//        }
//    }

}
