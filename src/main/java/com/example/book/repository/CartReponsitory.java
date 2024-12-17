package com.example.book.repository;

import com.example.book.models.Cart;
import org.springframework.dao.EmptyResultDataAccessException;
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
        try {
            String sql = "select * from cart where user_id=?";
            return jdbcTemplate.queryForObject(sql, cartRowMapper, userId);
        } catch (EmptyResultDataAccessException e) {
            return null;  // Nếu không tìm thấy bản ghi, trả về null
        }
    }
    public void save(Cart cart) {
        String sql="insert into cart(user_id) values(?)";
        jdbcTemplate.update(sql, cart.getUserId());
        Integer id = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        cart.setCartId(id);
    }
    public int delete(Integer id) {
        String sql="delete from cart where id=?";
        return jdbcTemplate.update(sql, id);
    }
}
