package com.example.book.repository;

import com.example.book.DTO.productDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class crudProductRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public crudProductRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // Truy vấn tất cả các sản phẩm
    public List<productDTO> getAllProducts() {
        String sql = "SELECT p.product_id, p.product_name, p.product_author, p.product_price, p.product_quantity, p.product_image, p.product_describe, c.category_name " +
                "FROM product p " +
                "JOIN category c ON p.category_id = c.id";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            productDTO product = new productDTO();
            product.setId(rs.getInt("product_id"));
            product.setProductName(rs.getString("product_name"));
            product.setProductAuthor(rs.getString("product_author"));
            product.setPrice(rs.getFloat("product_price"));
            product.setQuantity(rs.getInt("product_quantity"));
            product.setProductImage(rs.getString("product_image"));
            product.setProductDescription(rs.getString("product_describe"));
            product.setCategoryName(rs.getString("category_name"));
            return product;
        });
    }
    // Xóa sản phẩm theo ID
    public int deleteProductById(Integer id) {
        String sql = "DELETE FROM product WHERE product_id = ?";
        return jdbcTemplate.update(sql, id);
    }
}
