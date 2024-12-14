package com.example.book.models;

public class Product {
    private Integer id;
    private String productName;
    private String productAuthor;
    private float price;
    private int quantity;
    private String productImage;
    private String productDescription;
    private Integer categoryId;

    public Product() {
    }

    public Product(Integer id, String productName, String productAuthor, float price, int quantity,String productImage, String productDescription, Integer categoryId) {
        this.id = id;
        this.productName = productName;
        this.productAuthor = productAuthor;
        this.price = price;
        this.quantity = quantity;
        this.productImage = productImage;
        this.productDescription = productDescription;
        this.categoryId = categoryId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductAuthor() {
        return productAuthor;
    }

    public void setProductAuthor(String productAuthor) {
        this.productAuthor = productAuthor;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
