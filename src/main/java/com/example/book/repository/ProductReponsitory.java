package com.example.book.repository;

import com.example.book.models.Product;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductReponsitory {
    private final JdbcTemplate jdbcTemplate;

    public ProductReponsitory(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    private final RowMapper<Product> productRowMapper =(rs, rowNum) -> {
        Product product = new Product();
        product.setId(rs.getInt("product_id"));
        product.setProductName(rs.getString("product_name"));
        product.setProductAuthor(rs.getString("product_author"));
        product.setPrice(rs.getFloat("product_price"));
        product.setProductImage(rs.getString("product_image"));
        product.setProductDescription(rs.getString("product_describe"));
        product.setQuantity(rs.getInt("product_quantity"));
        product.setCategoryId(rs.getInt("category_id"));
        return product;
    };
    public List<Product> findAll() {
        String sql = "select * from product";
        return jdbcTemplate.query(sql, productRowMapper);
    }
    public Product findById(Integer id) {
        String sql = "select * from product where product_id=?";
        return jdbcTemplate.queryForObject(sql, productRowMapper, id);

    }
    public int addProduct(Product product) {
        String sql="insert into product (product_id,product_name,product_author,product_quantity,product_price,product_image,product_describe,category_id) values(?,?,?,?,?,?,?,?)";
        return jdbcTemplate.update(sql,product.getId(),product.getProductName(),product.getProductAuthor(),product.getQuantity(),product.getPrice(),product.getProductImage(),product.getProductDescription(),product.getCategoryId());
    }
    public int updateProduct(Product product) {
        String sql="update product set product_name=?, product_author=?, product_quantity=?, product_price=?, product_image=?, product_describe=?, category_id=? where id=?";
        return jdbcTemplate.update(sql,product.getProductName(),product.getProductAuthor(),product.getQuantity(),product.getPrice(),product.getProductImage(),product.getProductDescription(),product.getCategoryId(),product.getId());
    }
    public int deleteProduct(Integer id) {
        String sql="delete from product where product_id=?";
        return jdbcTemplate.update(sql,id);
    }
    public List<Product> findByCategoryId(Integer categoryId) {
        String sql = "select * from product where category_id=?";
        return jdbcTemplate.query(sql, productRowMapper, categoryId);
    }
}
