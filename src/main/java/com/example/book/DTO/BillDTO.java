package com.example.book.DTO;

import com.example.book.models.Bill;
import com.example.book.models.User;

public class BillDTO {
    private Bill bill;
    private User user;

    public BillDTO() {
    }

    public BillDTO(Bill bill, User user) {
        this.bill = bill;
        this.user = user;
    }

    public Bill getBill() {
        return bill;
    }

    public User getUser() {
        return user;
    }

    public void setBill(Bill bill) {
        this.bill = bill;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
