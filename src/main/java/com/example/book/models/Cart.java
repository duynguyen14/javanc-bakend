package com.example.book.models;

public class Cart {
    private Integer cartId;
    private Integer userId;

    public Cart() {
    }

    public Cart(Integer cartId, Integer userId) {
        this.cartId = cartId;
        this.userId = userId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    public Integer getCartId() {
        return cartId;
    }

    public void setCartId(Integer cartId) {
        this.cartId = cartId;
    }
}
