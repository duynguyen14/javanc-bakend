package com.example.book.DTO;

import com.example.book.models.Category;
import com.example.book.models.Product;

import java.util.List;

public class CategoryDTO {
    private List<Product> list;
    private Category category;

    public CategoryDTO() {
    }

    public CategoryDTO(List<Product> list, Category category) {
        this.list = list;
        this.category = category;
    }

    public List<Product> getList() {
        return list;
    }

    public void setList(List<Product> list) {
        this.list = list;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
