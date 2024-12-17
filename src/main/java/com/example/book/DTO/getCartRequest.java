package com.example.book.DTO;

import com.example.book.models.CartDetail;
import com.example.book.models.Product;

import java.util.List;

public class getCartRequest {
    private Product product;
    private CartDetail cartDetail;

    public getCartRequest() {
    }

    public getCartRequest(Product product, CartDetail cartDetail) {
        this.product = product;
        this.cartDetail = cartDetail;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public CartDetail getCartDetail() {
        return cartDetail;
    }

    public void setCartDetail(CartDetail cartDetail) {
        this.cartDetail = cartDetail;
    }
}
