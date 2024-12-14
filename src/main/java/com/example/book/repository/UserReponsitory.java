package com.example.book.repository;

import com.example.book.models.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserReponsitory {
    private final JdbcTemplate jdbcTemplate;
    public UserReponsitory(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    private final RowMapper<User> userRowMapper=(rs, rowNum) -> {
        User user = new User();
        user.setId(rs.getInt("id"));
        user.setUserName(rs.getString("username"));
        user.setPassword(rs.getString("password"));
        user.setEmail(rs.getString("email"));
        user.setPhone(rs.getString("phone"));
        user.setAddress(rs.getString("address"));
        user.setGender(rs.getString("gender"));
        return user;
    };
    public List<User> findAll() {
        return jdbcTemplate.query("select * from user", userRowMapper);
    }
    public User findById(Integer id) {
        return jdbcTemplate.queryForObject("select * from user where id=?", userRowMapper, id);
    }
    public int addUser(User user) {
        String sql="insert into user (username,password,email,phone,address,gender) values (?,?,?,?,?,?)";
        return jdbcTemplate.update(sql, user.getUserName(), user.getPassword(), user.getEmail(), user.getPhone(), user.getAddress(), user.getGender());
    }
    public int updateUser(User user) {
        String sql="update user set username=?,password=?,email=?,phone=?,address=? where id=?";
        return jdbcTemplate.update(sql, user.getUserName(), user.getPassword(), user.getEmail(), user.getPhone(), user.getAddress(), user.getId());
    }
    public int deleteUser(Integer id) {
        String sql="delete from user where id=?";
        return jdbcTemplate.update(sql, id);
    }
}
