package com.example.book.repository;

import com.example.book.models.Account;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AccountReponsitory {
    private JdbcTemplate jdbcTemplate;
    public AccountReponsitory(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    private final RowMapper<Account> accountRowMapper = (rs, rowNum) -> {
        Account account = new Account();
        account.setAccountId(rs.getInt("id"));
        account.setRole(rs.getString("role"));
        account.setUserId(rs.getInt("user_id"));
        return account;
    };
    public List<Account> findAll() {
        return jdbcTemplate.query("select * from account", accountRowMapper);
    }
    public Account findById(Integer id) {
        return jdbcTemplate.queryForObject("select * from account where id=?", accountRowMapper, id);
    }
    public Account findByUserId(Integer userId) {
        return jdbcTemplate.queryForObject("select * from account where user_id=?", accountRowMapper, userId);
    }
    public void save(Account account) {
        String sql = "insert into account(role,user_id) values(?,?)";
        jdbcTemplate.update(sql, account.getRole(), account.getUserId());
    }
}
