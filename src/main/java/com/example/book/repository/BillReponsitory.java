package com.example.book.repository;

import com.example.book.models.Bill;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
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
        bill.setTotal(rs.getDouble("total"));
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
        String sql = "INSERT INTO bill (total, doe, user_id) VALUES (?, ?, ?)";

        // Khởi tạo KeyHolder để lấy Generated Key
        KeyHolder keyHolder = new GeneratedKeyHolder();

        // Sử dụng JdbcTemplate để thực hiện câu lệnh SQL và lấy Generated Key
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setDouble(1, bill.getTotal());
            ps.setDate(2, new java.sql.Date(bill.getDate().getTime()));  // Chuyển Date sang java.sql.Date
            ps.setInt(3, bill.getUserID());
            return ps;
        }, keyHolder);

        // Lấy bill_id từ GeneratedKey và gán vào đối tượng Bill
        Integer billId = keyHolder.getKey().intValue();
        bill.setBillID(billId);  // Cập nhật BillID cho đối tượng Bill

        return billId;  // Trả về billId vừa được tạo
    }


    public int update(Bill bill) {
        String sql = "UPDATE bill SET quantity = ?, total = ?, doe = ?, methodPay = ?, user_id = ? WHERE bill_id = ?";

        return jdbcTemplate.update(sql,
                bill.getTotal(),
                bill.getDate(),
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
