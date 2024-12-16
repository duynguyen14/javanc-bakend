package com.example.book.repository;

import com.example.book.models.CartDetail;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CartDetailReponsitory {
    private  final JdbcTemplate jdbcTemplate;

    public CartDetailReponsitory(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    private final RowMapper<CartDetail> CartDetailRowMapper = (rs, rowNum) -> {
        CartDetail cartDetail = new CartDetail();
        cartDetail.setCartlID(rs.getInt("id"));
        cartDetail.setProductID(rs.getInt("product_id"));
        cartDetail.setQuantity(rs.getInt("quantity"));
        return cartDetail;
    };
    public CartDetail findById(int id) {
        String sql = "select * from cart_detail where id=?";
        return jdbcTemplate.queryForObject(sql, CartDetailRowMapper, id);
    }
    public List<CartDetail> findAll() {
        String sql = "select * from cart_detail";
        return jdbcTemplate.query(sql, CartDetailRowMapper);
    }
    public int addCart(CartDetail cartDetail) {
        String sql= "insert into cart_detail(product_id,quantity) values(?,?)";
        return jdbcTemplate.update(sql, cartDetail.getProductID(), cartDetail.getQuantity());
    }
    public int updateCart(CartDetail cartDetail) {
        String sql= "update cart_detail set quantity=? where id=? and product_id=?";
        return jdbcTemplate.update(sql, cartDetail.getQuantity(), cartDetail.getProductID(), cartDetail.getProductID());
    }
    public int deleteById(int id) {
        String sql= "delete from cart_detail where id=?";
        return jdbcTemplate.update(sql, id);
    }
}
