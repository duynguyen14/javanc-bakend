package com.example.book.controllers;

import com.example.book.DTO.RequestCreateBill;
import com.example.book.models.*;
import com.example.book.repository.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(path = "/api/bill")
@CrossOrigin("*")
public class billController {
    private final BillReponsitory billReponsitory;
    private final ProductReponsitory productReponsitory;
    private final BillReponsitory billReponsitory2;
    private final BillDetailReponsitory billDetailReponsitory;
    private final CartDetailReponsitory cartDetailReponsitory;
    private final CartReponsitory cartReponsitory;

    public billController(BillReponsitory billReponsitory, ProductReponsitory productReponsitory, BillReponsitory billReponsitory2, BillDetailReponsitory billDetailReponsitory, CartDetailReponsitory cartDetailReponsitory, CartReponsitory cartReponsitory) {
        this.billReponsitory = billReponsitory;
        this.productReponsitory = productReponsitory;
        this.billReponsitory2 = billReponsitory2;
        this.billDetailReponsitory = billDetailReponsitory;
        this.cartDetailReponsitory = cartDetailReponsitory;
        this.cartReponsitory = cartReponsitory;
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<?> findBillsByUserId(@PathVariable Integer userId) {
        List<Bill> bills = billReponsitory.findBillsByUserId(userId);
        if (bills.isEmpty()) {
            return new ResponseEntity<>("No bills found for the user", HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(bills, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findBillById(@PathVariable Integer id) {
        try {
            Bill bill = billReponsitory.findById(id);
            return new ResponseEntity<>(bill, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Bill not found", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("add/{id}")
    public ResponseEntity<?> addBill(@PathVariable Integer id, @RequestBody List<Product> listProduct) {
        try {
            // Kiểm tra nếu danh sách sản phẩm trống
            if (listProduct == null || listProduct.isEmpty()) {
                return new ResponseEntity<>("Danh sách sản phẩm không được trống.", HttpStatus.BAD_REQUEST);
            }
            Cart cart =cartReponsitory.findByUserId(id);
            // Tạo Bill mới
            Bill bill = new Bill();
            bill.setUserID(id);

            // Lấy thời gian hiện tại và chuyển đổi thành Date
            LocalDateTime localDateTime = LocalDateTime.now();
            Date date = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
            bill.setDate(date);

            double total = 0;
            List<BillDetail> listBillDetail = new ArrayList<>();

            // Duyệt qua các sản phẩm trong danh sách
            for (Product product : listProduct) {
                if (product.getQuantity() <= 0) {
                    return new ResponseEntity<>("Số lượng sản phẩm phải lớn hơn 0.", HttpStatus.BAD_REQUEST);
                }

                // Tạo BillDetail cho từng sản phẩm
                BillDetail billDetail = new BillDetail();
                billDetail.setQuantity(product.getQuantity());
                billDetail.setTotal(product.getPrice() * product.getQuantity());
                billDetail.setProductId(product.getId());

                // Thêm BillDetail vào danh sách
                listBillDetail.add(billDetail);

                // Tính tổng tiền của hóa đơn
                total += product.getPrice() * product.getQuantity();
            }

            // Cập nhật tổng tiền cho Bill
            bill.setTotal(total);

            // Lưu Bill vào cơ sở dữ liệu

            int rs =billReponsitory.save(bill);
            if (rs > 0) {
//                Integer billId = bill.getBillID(rs);  // Lấy BillID sau khi lưu Bill
                System.out.println(rs);
                // Lưu BillDetail vào cơ sở dữ liệu với BillID đã được tạo
                for (BillDetail billDetail : listBillDetail) {
                    billDetail.setBillId(rs);  // Gán BillID cho BillDetail
                    billDetailReponsitory.save(billDetail);
                }
                cartDetailReponsitory.deleteByCartID(cart.getCartId());
                cartReponsitory.delete(cart.getCartId());
                // Trả về kết quả thành công
                return new ResponseEntity<>("Bill added successfully", HttpStatus.OK);
            }
            else{
                return new ResponseEntity<>("Failed to save bill", HttpStatus.INTERNAL_SERVER_ERROR);
            }
            // Đảm bảo Bill đã được lưu và ID được tạo

        } catch (Exception e) {
            // Log lỗi nếu có
            e.printStackTrace();
            return new ResponseEntity<>("Failed to add bill: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    @PutMapping("/{id}")
    public ResponseEntity<?> updateBill(@PathVariable Integer id, @RequestBody Bill bill) {
        bill.setBillID(id);
        int rows = billReponsitory.update(bill);
        if (rows > 0) {
            return new ResponseEntity<>("Bill updated successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>("Failed to update bill", HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBill(@PathVariable Integer id) {
        billDetailReponsitory.deleteByBillId(id);
        int rows = billReponsitory.delete(id);
        if (rows > 0) {
            return new ResponseEntity<>("Bill deleted successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>("Failed to delete bill", HttpStatus.BAD_REQUEST);
    }
}
