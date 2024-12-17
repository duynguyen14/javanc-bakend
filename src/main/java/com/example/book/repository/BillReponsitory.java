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
        bill.setMethodPay(rs.getString("methodPay"));
        bill.setUserID(rs.getInt("user_id"));
        return bill;
    };

    public List<Bill> findAll() {
        String sql = "SELECT * FROM bill";
        return jdbcTemplate.query(sql, billRowMapper);
    }

    public Bill findById(Integer id) {
        String sql = "SELECT * FROM bill WHERE bill_id = ?";
        List<Bill> result = jdbcTemplate.query(sql, billRowMapper, id);
        return result.isEmpty() ? null : result.get(0);
    }

    public List<Bill> findBillsByUserId(Integer userId) {
        String sql = "SELECT * FROM bill WHERE user_id = ?";
        return jdbcTemplate.query(sql, billRowMapper, userId);
    }

    public int save(Bill bill) {
        String sql = "INSERT INTO bill (quantity, total, doe, methodPay, user_id) VALUES (?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql,
                bill.getQuantity(),
                bill.getTotal(),
                bill.getDate(),
                bill.getMethodPay(),
                bill.getUserID());
    }

    public int update(Bill bill) {
        String sql = "UPDATE bill SET quantity = ?, total = ?, doe = ?, methodPay = ?, user_id = ? WHERE bill_id = ?";

        return jdbcTemplate.update(sql,
                bill.getQuantity(),
                bill.getTotal(),
                bill.getDate(),
                bill.getMethodPay(),
                bill.getUserID(),
                bill.getBillID());
    }

    public int delete(Integer id) {
        String sqlChild = "DELETE FROM billdetail WHERE bill_id = ?";
        String sqlParent = "DELETE FROM bill WHERE bill_id = ?";
        jdbcTemplate.update(sqlChild, id);
        return jdbcTemplate.update(sqlParent, id);
    }

}
