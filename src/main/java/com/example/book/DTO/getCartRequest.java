package com.example.book.DTO;

import com.example.book.models.CartDetail;
import com.example.book.models.Product;

import java.util.List;

public class getCartRequest {
    private List<Product> list;
    private List<CartDetail> listCartDetail;

    public getCartRequest() {
    }

    public getCartRequest(List<Product> list, List<CartDetail> listCartDetail) {
        this.list = list;
        this.listCartDetail = listCartDetail;
    }

    public List<Product> getList() {
        return list;
    }

    public void setList(List<Product> list) {
        this.list = list;
    }

    public List<CartDetail> getListCartDetail() {
        return listCartDetail;
    }

    public void setListCartDetail(List<CartDetail> listCartDetail) {
        this.listCartDetail = listCartDetail;
    }
}
