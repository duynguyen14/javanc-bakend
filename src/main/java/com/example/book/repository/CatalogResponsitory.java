package com.example.book.repository;

import com.example.book.models.Catalog;
import org.springframework.data.relational.core.sql.In;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;

import java.awt.print.Book;
import java.util.List;

@Repository
public class CatalogResponsitory {
    private final JdbcTemplate jdbcTemplate;
    public CatalogResponsitory(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    private final RowMapper<Catalog> catalogRowMapper = (rs, rowNum) -> {
        Catalog catalog = new Catalog();
        catalog.setCatalogId(rs.getInt("id"));
        catalog.setCatalogName(rs.getString("catalog_name"));
        return catalog;
    };
    public List<Catalog> findAll() {
        return jdbcTemplate.query("select * from catalog", catalogRowMapper);
    }
    public Catalog findByCatalogId(int catalogId) {
        String sql = "select * from catalog where id = ?";
        return jdbcTemplate.queryForObject(sql, catalogRowMapper, catalogId);
    }
    public int addCatalog(Catalog catalog) {
        String sql = "insert into catalog (catalog_name) values (?)";
        return jdbcTemplate.update(sql, catalog.getCatalogName());
    }
    public int updateCatalog(Catalog catalog) {
        String sql = "update catalog set catalog_name=? where id=?";
        return jdbcTemplate.update(sql,catalog.getCatalogName(),catalog.getCatalogId());
    }
    public int deleteCatalog(int catalogId) {
        String sql = "delete from catalog where id=?";
        return jdbcTemplate.update(sql,catalogId);
    }
}
