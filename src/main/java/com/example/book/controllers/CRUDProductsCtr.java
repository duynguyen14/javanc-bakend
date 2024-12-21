package com.example.book.controllers;
import com.example.book.DTO.CatalogDTO;
import com.example.book.DTO.CategoryDTO;
import com.example.book.DTO.productDTO;
import com.example.book.models.Catalog;
import com.example.book.models.Category;
import com.example.book.models.Product;
import com.example.book.repository.CatalogResponsitory;
import com.example.book.repository.CategoryReponsitory;
import com.example.book.repository.ProductReponsitory;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.book.repository.crudProductRepository;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/crud_products")
@CrossOrigin("*")
public class CRUDProductsCtr {
    @Autowired
    private ProductReponsitory productReponsitory;
    @Autowired
    private CategoryReponsitory categoryRepository;

    @Autowired
    private CatalogResponsitory catalogResponsitory ;
    private final crudProductRepository crud;

    @Autowired  // Đảm bảo @Autowired ở constructor
    public CRUDProductsCtr(crudProductRepository crud) {
        this.crud = crud;
    }
    // Lấy danh sách danh mục
    @GetMapping("/catalogs")
    public List<Catalog> getCatalogs() {
        return catalogResponsitory.findAll();
    }
    @GetMapping("/categories")
    public List<Category> getCategoriesByCatalog() {
        return categoryRepository.findAll();
    }
    // API lấy danh sách sản phẩm với thông tin đầy đủ
    @GetMapping("")
    public ResponseEntity<List<productDTO>> getAllProductsWithCategory() {
        List<productDTO> products = crud.getAllProducts();
        return ResponseEntity.ok(products);
    }
    @PostMapping("/add")
    public ResponseEntity<String> addProduct(@RequestBody Product product) {
        try {
            int rowsAffected = productReponsitory.addProduct(product);
            if (rowsAffected > 0) {
                return ResponseEntity.ok("Thêm sản phẩm thành công.");
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Không thể thêm sản phẩm.");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Lỗi khi thêm sản phẩm: " + e.getMessage());
        }
    }
    // cập nhật sản phẩm
    @PutMapping("/update")
    public ResponseEntity<String> updateProduct(@RequestBody Product product) {
        try {
            int rowsAffected = productReponsitory.updateProduct(product);
            if (rowsAffected > 0) {
                return ResponseEntity.ok("Cập nhật sản phẩm thành công.");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Không tìm thấy sản phẩm để cập nhật.");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Lỗi khi cập nhật sản phẩm: " + e.getMessage());
        }
    }
    // API xóa sản phẩm
//    @DeleteMapping("/{id}")
//    public ResponseEntity<String> deleteProduct(@PathVariable Integer id) {
//        int result = crud.deleteProductById(id);
//        if (result > 0) {
//            return ResponseEntity.ok("Product deleted successfully.");
//        } else {
//            return ResponseEntity.status(404).body("Product not found.");
//        }
//    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Integer id) {
        try{
            Product product = productReponsitory.findById(id);
            if (product != null) {
                System.out.println(product.getId());
                int rs=productReponsitory.deleteProduct(product.getId());
                if(rs>0){
                    return ResponseEntity.status(HttpStatus.OK).body("Xóa sản phẩm than công");
                }
                else{
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Xóa thất bại");
                }
            }
            else{
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Không tìm thấy sản phẩm");
            }
        }
        catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Lỗi server");
        }
    }
}

