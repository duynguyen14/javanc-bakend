package com.example.book.repository;

import com.example.book.models.Category;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CategoryReponsitory {
    private final JdbcTemplate jdbcTemplate;
    public CategoryReponsitory(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    private final RowMapper<Category> CategoryMapper = (rs, rowNum) -> {
        Category category = new Category();
        category.setCatalogId(rs.getInt("id"));
        category.setCategoryName(rs.getString("category_name"));
        category.setCatalogId(rs.getInt("catalog_id"));
        return category;
    };
    public List<Category> findAll() {
        return jdbcTemplate.query("select * from category", CategoryMapper);
    }
    public Category findById(Integer id) {
        return jdbcTemplate.queryForObject("select * from category where id=?", CategoryMapper, id);
    }
    public int addCategory(Category category) {
        String sql = "insert into category(category_name) values(?)";
        return jdbcTemplate.update(sql,category.getCategoryName());
    }
    public int updateCategory(Category category) {
        String sql = "update category set category_name=? where id=?";
        return jdbcTemplate.update(sql,category.getCategoryName(),category.getCatalogId());
    }
    public int deleteCategory(Integer id) {
        String sql = "delete from category where id=?";
        return jdbcTemplate.update(sql,id);
    }
}
