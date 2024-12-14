package com.example.book.models;

public class Account {
    private Integer accountId;
    private String role;
    private Integer userId;
    public Account(){

    }
    public Account(Integer accountId, String role, Integer userId) {
        this.accountId = accountId;
        this.role = role;
        this.userId = userId;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
