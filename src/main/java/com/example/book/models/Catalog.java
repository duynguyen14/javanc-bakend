package com.example.book.models;

//import org.springframework.data.relational.core.sql.In;

public class Catalog {
    private Integer CatalogId;
    private String CatalogName;

    public Catalog() {
    }

    public Catalog(Integer catalogId, String catalogName) {
        CatalogId = catalogId;
        CatalogName = catalogName;
    }

    public Integer getCatalogId() {
        return CatalogId;
    }

    public void setCatalogId(Integer catalogId) {
        CatalogId = catalogId;
    }

    public String getCatalogName() {
        return CatalogName;
    }

    public void setCatalogName(String catalogName) {
        CatalogName = catalogName;
    }
}
