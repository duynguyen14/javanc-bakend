package com.example.book.DTO;

import com.example.book.models.CartDetail;
import com.example.book.models.Product;

import java.util.List;

public class RequestCreateBill {
    private List<Product> productList;
    private List<CartDetail> cartDetailList;

    public RequestCreateBill() {
    }

    public RequestCreateBill(List<Product> productList, List<CartDetail> cartDetailList) {
        this.productList = productList;
        this.cartDetailList = cartDetailList;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    public List<CartDetail> getCartDetailList() {
        return cartDetailList;
    }

    public void setCartDetailList(List<CartDetail> cartDetailList) {
        this.cartDetailList = cartDetailList;
    }
}
