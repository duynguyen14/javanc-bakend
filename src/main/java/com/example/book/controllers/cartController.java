package com.example.book.controllers;

import com.example.book.DTO.getCartRequest;
import com.example.book.models.Cart;
import com.example.book.models.CartDetail;
import com.example.book.models.Product;
import com.example.book.repository.CartDetailReponsitory;
import com.example.book.repository.CartReponsitory;
import com.example.book.repository.ProductReponsitory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/cart")
@CrossOrigin("*")
public class cartController {
    private final CartReponsitory  cartReponsitory;
    private final CartDetailReponsitory cartDetailReponsitory;
    private final ProductReponsitory productReponsitory;
    public cartController(CartReponsitory cartReponsitory, CartDetailReponsitory cartDetailReponsitory, ProductReponsitory productReponsitory) {
        this.cartReponsitory = cartReponsitory;
        this.cartDetailReponsitory = cartDetailReponsitory;
        this.productReponsitory = productReponsitory;
    }

    // Lấy tất cả Cart
//    @GetMapping
//    public ResponseEntity<List<getCartRequest>> getAllCarts() {
//
//
//    }

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
    public ResponseEntity<List> getCartByUserId(@PathVariable Integer userId) {
        Cart cart = cartReponsitory.findByUserId(userId);
        List<getCartRequest> listCartRequest = new ArrayList<>();
        if (cart == null) {
            // Tạo mới Cart nếu không tìm thấy
            Cart newCart = new Cart();
            newCart.setUserId(userId);
            cartReponsitory.save(newCart);
            return new ResponseEntity<>(listCartRequest, HttpStatus.CREATED);
        } else {
            List<CartDetail> listCartDetail= cartDetailReponsitory.findByCartID(cart.getCartId());
            for (CartDetail cartDetail : listCartDetail) {
                Product product = new Product();
                product=productReponsitory.findById(cartDetail.getProductID());
                getCartRequest cartRequest = new getCartRequest();
                cartRequest.setCartDetail(cartDetail);
                cartRequest.setProduct(product);
                listCartRequest.add(cartRequest);
            }
            return new ResponseEntity<>(listCartRequest, HttpStatus.OK);
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
