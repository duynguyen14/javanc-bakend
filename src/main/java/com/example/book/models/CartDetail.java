package com.example.book.models;

import org.springframework.data.relational.core.sql.In;

public class CartDetail {
    private Integer cartlID;
    private int quantity;
    private Integer productID;

    public CartDetail() {
    }

    public CartDetail(Integer cartlID, int quantity, Integer productID) {
        this.cartlID = cartlID;
        this.quantity = quantity;
        this.productID = productID;
    }

    public Integer getCartlID() {
        return cartlID;
    }

    public void setCartlID(Integer cartlID) {
        this.cartlID = cartlID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Integer getProductID() {
        return productID;
    }

    public void setProductID(Integer productID) {
        this.productID = productID;
    }
}
