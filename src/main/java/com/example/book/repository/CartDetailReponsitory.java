package com.example.book.repository;

import com.example.book.models.CartDetail;
import org.springframework.dao.EmptyResultDataAccessException;
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
        cartDetail.setCartlID(rs.getInt("cart_id"));
        cartDetail.setProductID(rs.getInt("product_id"));
        cartDetail.setQuantity(rs.getInt("quantity"));
        return cartDetail;
    };
    public CartDetail findById(int id) {
        String sql = "select * from cartdetail where id=?";
        return jdbcTemplate.queryForObject(sql, CartDetailRowMapper, id);
    }
    public CartDetail findByProductIDandCartID(Integer productID, Integer cartID) {
        try{
            String sql = "select * from cartdetail where product_id=? and cart_id=?";
            return jdbcTemplate.queryForObject(sql, CartDetailRowMapper, productID, cartID);
        }
        catch(EmptyResultDataAccessException e){
            return null;
        }
    }
    public List<CartDetail> findAll() {
        String sql = "select * from cartdetail";
        return jdbcTemplate.query(sql, CartDetailRowMapper);
    }
    public int save(CartDetail cartDetail) {
        String sql= "insert into cartdetail(cart_id,product_id,quantity) values(?,?,?)";
        return jdbcTemplate.update(sql,cartDetail.getCartlID(), cartDetail.getProductID(), cartDetail.getQuantity());
    }
    public int updateCart(CartDetail cartDetail) {
        String sql= "update cartdetail set quantity=? where cart_id=? and product_id=?";
        return jdbcTemplate.update(sql, cartDetail.getQuantity(), cartDetail.getCartlID(), cartDetail.getProductID());
    }
    public int deleteByIDandProductID(Integer productID, Integer cartID) {
        String sql= "delete from cartdetail where product_id=? and cart_id=?";
        return jdbcTemplate.update(sql, productID, cartID);
    }
    public List<CartDetail> findByCartID(Integer cartID) {
        String sql = "select * from cartdetail where cart_id=?";
        return jdbcTemplate.query(sql, CartDetailRowMapper, cartID);
    }
}
