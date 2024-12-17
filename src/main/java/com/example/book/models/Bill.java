package com.example.book.models;

import java.util.Date;

public class Bill {
    private Integer billID;
    private double total;
    private Date date;
    private Integer userID;

    public Bill(){

    }
    public Bill(Integer billID, double total, Date date, Integer userID) {
        this.billID = billID;
        this.total = total;
        this.date = date;
        this.userID = userID;

    }

    public Integer getBillID() {
        return billID;
    }

    public void setBillID(Integer billID) {
        this.billID = billID;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }
}
