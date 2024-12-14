package com.example.book.models;

import org.springframework.data.relational.core.sql.In;

public class Category {
    private Integer Categoryid;
    private String categoryName;
    private Integer CatalogId;

    public Category() {
    }

    public Category(Integer Categoryid, String categoryName, Integer catalogId) {
        this.Categoryid = Categoryid;
        this.categoryName = categoryName;
        CatalogId = catalogId;
    }

    public Integer getCategoryid() {
        return Categoryid;
    }

    public void setCategoryid(Integer id) {
        this.Categoryid = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Integer getCatalogId() {
        return CatalogId;
    }

    public void setCatalogId(Integer catalogId) {
        CatalogId = catalogId;
    }
}
