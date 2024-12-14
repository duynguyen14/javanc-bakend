package com.example.book.repository;

import com.example.book.models.BillDetail;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BillDetailReponsitory {
    private JdbcTemplate jdbcTemplate;
    public BillDetailReponsitory(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    private final RowMapper<BillDetail> billDetailRowMapper = (rs, rowNum) -> {
        BillDetail billDetail = new BillDetail();
        billDetail.setBillDetailId(rs.getInt("billdetail_id"));
        billDetail.setBillId(rs.getInt("bill_id"));
        billDetail.setDate(rs.getDate("date"));
        billDetail.setQuantity(rs.getInt("quantity"));
        billDetail.setTotal(rs.getDouble("total"));
        billDetail.setProductId(rs.getInt("product_id"));
        return billDetail;
    };
    public List<BillDetail> findAll() {
        return jdbcTemplate.query("select * from bill_detail", billDetailRowMapper);
    }
    public BillDetail findByBillDetailId(int billDetailId) {
        return jdbcTemplate.queryForObject("select*from billdetail where billdetail_id=?", billDetailRowMapper, billDetailId);
    }

}
