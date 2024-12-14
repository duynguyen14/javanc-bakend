package com.example.book.models;

import java.util.Date;

public class Bill {
    private Integer billID;
    private int quantity;
    private double total;
    private Date date;
    private String methodPay;
    private Integer userID;

    public Bill(){

    }
    public Bill(Integer billID, int quantity, double total, Date date, String methodPay, Integer userID) {
        this.billID = billID;
        this.quantity = quantity;
        this.total = total;
        this.date = date;
        this.methodPay = methodPay;
        this.userID = userID;

    }

    public Integer getBillID() {
        return billID;
    }

    public void setBillID(Integer billID) {
        this.billID = billID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
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

    public String getMethodPay() {
        return methodPay;
    }

    public void setMethodPay(String methodPay) {
        this.methodPay = methodPay;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }
}
