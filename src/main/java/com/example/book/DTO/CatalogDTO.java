package com.example.book.DTO;

import com.example.book.models.Catalog;

import java.util.List;

public class CatalogDTO {
    private Catalog catalog;
    private List<CategoryDTO> list;

    public CatalogDTO() {
    }

    public CatalogDTO(Catalog catalog, List<CategoryDTO> list) {
        this.catalog = catalog;
        this.list = list;
    }

    public Catalog getCatalog() {
        return catalog;
    }

    public void setCatalog(Catalog catalog) {
        this.catalog = catalog;
    }

    public List<CategoryDTO> getList() {
        return list;
    }

    public void setList(List<CategoryDTO> list) {
        this.list = list;
    }
}
