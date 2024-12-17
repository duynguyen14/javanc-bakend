package com.example.book.DTO;

import com.example.book.models.Bill;
import com.example.book.models.BillDetail;
import com.example.book.models.Product;

public class BillDetailDTO {
    private BillDetail billDetail;
    private Bill bill;
    private Product product;

    public BillDetailDTO() {
    }

    public BillDetailDTO(BillDetail billDetail, Bill bill, Product product) {
        this.billDetail = billDetail;
        this.bill = bill;
        this.product = product;
    }

    public BillDetail getBillDetail() {
        return billDetail;
    }

    public Bill getBill() {
        return bill;
    }

    public Product getProduct() {
        return product;
    }

    public void setBillDetail(BillDetail billDetail) {
        this.billDetail = billDetail;
    }

    public void setBill(Bill bill) {
        this.bill = bill;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
