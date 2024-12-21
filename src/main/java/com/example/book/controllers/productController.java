package com.example.book.controllers;

import com.example.book.DTO.CatalogDTO;
import com.example.book.DTO.CategoryDTO;
import com.example.book.models.Catalog;
import com.example.book.models.Category;
import com.example.book.models.Product;
import com.example.book.repository.CatalogResponsitory;
import com.example.book.repository.CategoryReponsitory;
import com.example.book.repository.ProductReponsitory;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/api/products")
@CrossOrigin("*")
public class productController {
    private final ProductReponsitory productReponsitory;
    private final CategoryReponsitory categoryReponsitory;
    private final CatalogResponsitory catalogReponsitory;
    public productController(ProductReponsitory productReponsitory, CategoryReponsitory categoryReponsitory, CatalogResponsitory catalogReponsitory) {
        this.productReponsitory = productReponsitory;
        this.categoryReponsitory = categoryReponsitory;
        this.catalogReponsitory = catalogReponsitory;
    }

    @GetMapping("")
    List<Product> getAllProducts() {
        return productReponsitory.findAll();
    }
    @GetMapping("/{id}")
    Product getProductById(@PathVariable Integer id) {
        return productReponsitory.findById(id);
    }
    @GetMapping("/category/{id}")
    CategoryDTO getProductsByCategory(@PathVariable Integer id) {
        List<Product> list=productReponsitory.findByCategoryId(id);
        Category category = categoryReponsitory.findById(id);
        System.out.println(category.getCategoryid());
        CategoryDTO categoryDTO=new CategoryDTO();
        categoryDTO.setCategory(category);
        categoryDTO.setList(list);
        return categoryDTO;
    }
    @GetMapping("/catalog/{id}")
    CatalogDTO getProductsByCatalog(@PathVariable Integer id) {
        CatalogDTO catalogDTO=new CatalogDTO();
        Catalog catalog=catalogReponsitory.findByCatalogId(id);
        List<Category>list= categoryReponsitory.findByCatalogId(id);
        List<CategoryDTO> listCategoryDTO=new ArrayList<>();
        for (Category category : list) {
            CategoryDTO categoryDTO=new CategoryDTO();
            categoryDTO.setCategory(category);
            List<Product> listProduct= productReponsitory.findByCategoryId(id);
            categoryDTO.setList(listProduct);
            listCategoryDTO.add(categoryDTO);
        }
        catalogDTO.setList(listCategoryDTO);
        catalogDTO.setCatalog(catalog);
        return catalogDTO;
    }
    @GetMapping("/search/{productName}")
    List<Product> searchProductByProductName(@PathVariable String productName) {
        if(productName==null || productName.length()==0) {
            return new ArrayList<>();
        }
        else{
            List<Product> productList = productReponsitory.findByName(productName);
            return productList;
        }

    }
}
