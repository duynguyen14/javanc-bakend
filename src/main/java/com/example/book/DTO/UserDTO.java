package com.example.book.DTO;

import com.example.book.models.Account;
import com.example.book.models.User;

public class UserDTO {
    private User user;
    private Account account;

    public UserDTO() {
    }

    public UserDTO(User user, Account account) {
        this.user = user;
        this.account = account;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
