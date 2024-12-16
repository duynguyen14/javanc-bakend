package com.example.book.repository;

import com.example.book.models.Cart;
import org.springframework.data.relational.core.sql.In;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CartReponsitory {
    private final JdbcTemplate jdbcTemplate;
    public CartReponsitory(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    private final RowMapper<Cart> cartRowMapper = (rs, rowNum) -> {
        Cart cart = new Cart();
        cart.setCartId(rs.getInt("id"));
        cart.setProductId(rs.getInt("product_id"));
        cart.setQuantity(rs.getInt("quantity"));
        cart.setUserId(rs.getInt("user_id"));
        return cart;
    };
    public List<Cart> findAll() {
        String sql = "select * from cart";
        return jdbcTemplate.query(sql, cartRowMapper);
    }
    public Cart findById(Integer id) {
        String sql = "select * from cart where id=?";
        return jdbcTemplate.queryForObject(sql, cartRowMapper, id);
    }
    public Cart findByUserId(Integer userId) {
        String sql = "select * from cart where user_id=?";
        return jdbcTemplate.queryForObject(sql, cartRowMapper, userId);
    }
    public int save(Cart cart) {
        String sql="insert into cart(user_id) values(?)";
        return jdbcTemplate.update(sql, cart.getUserId());
    }
    public int update(Cart cart) {
        String sql="update cart set quantity= ? where user_id=? and id= ?";
        return jdbcTemplate.update(sql, cart.getQuantity(), cart.getUserId(),cart.getCartId());
    }
}
