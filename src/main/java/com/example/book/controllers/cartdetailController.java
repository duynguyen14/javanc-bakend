package com.example.book.controllers;

import com.example.book.DTO.AddCartRequest;
import com.example.book.models.Cart;
import com.example.book.models.CartDetail;
import com.example.book.repository.CartDetailReponsitory;
import com.example.book.repository.CartReponsitory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cartdetail")
@CrossOrigin("*")
public class cartdetailController {
    private final CartReponsitory cartReponsitory;
    private final CartDetailReponsitory cartDetailReponsitory;

    public cartdetailController(CartReponsitory cartReponsitory, CartDetailReponsitory cartDetailReponsitory) {
//        this.cartdetailController = cartdetailController;
        this.cartReponsitory = cartReponsitory;
        this.cartDetailReponsitory = cartDetailReponsitory;
    }
    @GetMapping("/get")
    public List<CartDetail> findAll() {
        return cartDetailReponsitory.findAll();
    }

    public CartDetail findById(Integer id) {
        return cartDetailReponsitory.findById(id);
    }
//    thêm san phẩm vào giỏ
    @CrossOrigin("*")
    @PostMapping("/add/{id}")
    public ResponseEntity<String> AddProductToCart(@PathVariable Integer id, @RequestBody AddCartRequest addCartRequest) {
        try{
//            System.out.println(addCartRequest.getQuantity());
            Cart cart = cartReponsitory.findByUserId(id);
            System.out.println("cart: " + cart);
            if(cart == null) {
                cart = new Cart();
                cart.setUserId(id);
                cartReponsitory.save(cart);
            }
            CartDetail cartDetail = cartDetailReponsitory.findByProductIDandCartID(addCartRequest.getProductId(),cart.getCartId());
            if(cartDetail == null) {
                cartDetail = new CartDetail();
                cartDetail.setProductID(addCartRequest.getProductId());
                cartDetail.setQuantity(addCartRequest.getQuantity());
                cartDetail.setCartlID(cart.getCartId());
                cartDetailReponsitory.save(cartDetail);
                return ResponseEntity.status(HttpStatus.CREATED).body("Sản phẩm đã được thêm vào giỏ hàng.");
            }
            else{
                cartDetail.setQuantity(cartDetail.getQuantity() + addCartRequest.getQuantity());
//                cartDetail.setProductID(addCartRequest.getProductId());
//                cartDetail.setCartlID(cart.getCartId());
                System.out.println(cartDetail.getProductID());
                System.out.println(cartDetail.getQuantity());
                System.out.println(cartDetail.getCartlID());
                int rs=cartDetailReponsitory.updateCart(cartDetail);
                if(rs > 0) {
                    return ResponseEntity.status(HttpStatus.OK).body("Số lượng sản phẩm trong giỏ đã được cập nhật.");
                }
                else{
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Lỗi");
                }

            }
        }
        catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Đã xảy ra lỗi: " + e.getMessage());
        }
//        return ResponseEntity.status(HttpStatus.OK).body("ok");

    }
//    chỉnh sửa số lượng sản phẩm trong giỏ
    @PatchMapping("/patch/{id}")
    public ResponseEntity<String> PatchCart(@PathVariable Integer id, @RequestBody AddCartRequest addCartRequest) {
        Cart cart = cartReponsitory.findByUserId(id);
        CartDetail cartDetail= cartDetailReponsitory.findByProductIDandCartID(addCartRequest.getProductId(),cart.getCartId());
        cartDetail.setQuantity(addCartRequest.getQuantity());
        cartDetailReponsitory.save(cartDetail);
        return ResponseEntity.status(HttpStatus.OK).body("Chỉnh sửa số lượng san phẩm thành công");
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> DeleteCart(@PathVariable Integer id, @RequestBody Integer productId) {
        try {
            // Kiểm tra xem giỏ hàng có tồn tại không
            Cart cart = cartReponsitory.findByUserId(id);
            if (cart == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Giỏ hàng không tồn tại.");
            }

            // Tìm sản phẩm trong giỏ hàng
            CartDetail cartDetail = cartDetailReponsitory.findByProductIDandCartID(productId, cart.getCartId());
            if (cartDetail == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Sản phẩm không tồn tại trong giỏ hàng.");
            }

            // Xóa sản phẩm khỏi giỏ hàng
            int rs = cartDetailReponsitory.deleteByIDandProductID(productId, cart.getCartId());
            if (rs > 0) {
                return ResponseEntity.status(HttpStatus.OK).body("Xóa sản phẩm thành công.");
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Không thể xóa sản phẩm. Vui lòng thử lại sau.");
            }
        } catch (Exception e) {
            // Trả về lỗi nếu có exception
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Đã xảy ra lỗi: " + e.getMessage());
        }
    }

}
