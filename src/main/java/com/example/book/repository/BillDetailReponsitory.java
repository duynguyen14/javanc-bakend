package com.example.book.repository;

import com.example.book.DTO.BillDetailDTO;
import com.example.book.models.BillDetail;
import com.example.book.models.Product;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BillDetailReponsitory {
    private final JdbcTemplate jdbcTemplate;

    public BillDetailReponsitory(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // Mapping dữ liệu từ kết quả truy vấn
//    private final RowMapper<BillDetail> billDetailRowMapper = (rs, rowNum) -> {
//        BillDetail billDetail = new BillDetail();
//        billDetail.setBillDetailId(rs.getInt("billdetail_id"));
//        billDetail.setBillId(rs.getInt("bill_id"));
//        billDetail.setProductId(rs.getInt("product_id"));
//        billDetail.setQuantity(rs.getInt("quantity"));
//        billDetail.setTotal(rs.getDouble("total"));
//        billDetail.setDate(rs.getDate("doe"));
//        return billDetail;
//    };

    // Mapping dữ liệu từ kết quả truy vấn với thông tin chi tiết hóa đơn và sản phẩm
    private final RowMapper<BillDetailDTO> billDetailDTORowMapper = (rs, rowNum) -> {
        BillDetail billDetail = new BillDetail();
        billDetail.setBillDetailId(rs.getInt("billdetail_id"));
        billDetail.setBillId(rs.getInt("bill_id"));
        billDetail.setProductId(rs.getInt("product_id"));
        billDetail.setQuantity(rs.getInt("quantity"));
        billDetail.setTotal(rs.getDouble("total"));
        billDetail.setDate(rs.getDate("doe"));

        Product product = new Product();
        product.setId(rs.getInt("product_id"));
        product.setProductName(rs.getString("product_name"));
        product.setProductAuthor(rs.getString("product_author"));
        product.setPrice(rs.getFloat("product_price"));
        product.setQuantity(rs.getInt("product_quantity"));
        product.setProductImage(rs.getString("product_image"));
        product.setProductDescription(rs.getString("product_describe"));
        product.setCategoryId(rs.getInt("category_id"));

        return new BillDetailDTO(billDetail, null, product);  // Bill có thể null nếu không cần thông tin hóa đơn
    };

    public List<BillDetailDTO> findAll() {
        String sql = "SELECT bd.*, p.product_name, p.product_author, p.product_price, p.product_quantity, " +
                "p.product_image, p.product_describe, p.category_id " +
                "FROM billdetail bd " +
                "JOIN product p ON bd.product_id = p.product_id";
        return jdbcTemplate.query(sql, billDetailDTORowMapper);
    }

    public List<BillDetailDTO> findByBillId(int billId) {
        String sql = "SELECT bd.*, p.product_name, p.product_author, p.product_price, p.product_quantity, " +
                "p.product_image, p.product_describe, p.category_id " +
                "FROM billdetail bd " +
                "JOIN product p ON bd.product_id = p.product_id " +
                "WHERE bd.bill_id = ?";
        return jdbcTemplate.query(sql, billDetailDTORowMapper, billId);
    }

    public int save(BillDetail billDetail) {
        String sql = "INSERT INTO billdetail (bill_id, product_id, quantity, total, doe) VALUES (?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql,
                billDetail.getBillId(),
                billDetail.getProductId(),
                billDetail.getQuantity(),
                billDetail.getTotal(),
                billDetail.getDate());
    }

    public int update(BillDetail billDetail) {
        String sql = "UPDATE billdetail SET bill_id = ?, product_id = ?, quantity = ?, total = ?, doe = ? WHERE billdetail_id = ?";
        return jdbcTemplate.update(sql,
                billDetail.getBillId(),
                billDetail.getProductId(),
                billDetail.getQuantity(),
                billDetail.getTotal(),
                billDetail.getDate(),
                billDetail.getBillDetailId());
    }

    public int delete(int billDetailId) {
        String sql = "DELETE FROM billdetail WHERE billdetail_id = ?";
        return jdbcTemplate.update(sql, billDetailId);
    }
}
