package com.example.book.repository;

import com.example.book.models.Bill;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BillReponsitory {
    private final JdbcTemplate jdbcTemplate;
    public BillReponsitory(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    private final RowMapper<Bill> billRowMapper = (rs, rowNum) -> {
        Bill bill = new Bill();
        bill.setBillID(rs.getInt("bill_id"));
        bill.setDate(rs.getDate("doe"));
        bill.setQuantity(rs.getInt("quantity"));
        bill.setTotal(rs.getDouble("total"));
        bill.setMethodPay(rs.getString("method_pay"));
        bill.setUserID(rs.getInt("user_id"));
        return bill;
    };
    public List<Bill> findAll() {
        return jdbcTemplate.query("select * from bill", billRowMapper);
    }
    public Bill findById(Integer id) {
        return jdbcTemplate.queryForObject("select * from bill where bill_id = ?", billRowMapper, id);
    }

}
