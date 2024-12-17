package com.example.book.controllers;

import com.example.book.DTO.BillDetailDTO;
import com.example.book.models.Bill;
import com.example.book.models.BillDetail;
import com.example.book.repository.BillDetailReponsitory;
import com.example.book.repository.BillReponsitory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/billdetail")
@CrossOrigin("*")
public class billDetailController {
    private final BillDetailReponsitory billDetailRepository;
    private final BillReponsitory billReponsitory;

    public billDetailController(BillDetailReponsitory billDetailRepository, BillReponsitory billReponsitory) {
        this.billDetailRepository = billDetailRepository;
        this.billReponsitory = billReponsitory;
    }

    @GetMapping("")
    public ResponseEntity<?> getAllBillDetails() {
        List<BillDetailDTO> billDetailDTOs = billDetailRepository.findAll();
        if (billDetailDTOs.isEmpty()) {
            return new ResponseEntity<>("No bill details found", HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(billDetailDTOs, HttpStatus.OK);
    }

//    // Lấy chi tiết hóa đơn theo ID và thông tin sản phẩm
//    @GetMapping("/{id}")
//    public ResponseEntity<?> getBillDetailById(@PathVariable Integer id) {
//        try {
//            BillDetailDTO billDetailDTO = billDetailRepository.findByBillDetailId(id);
//            if (billDetailDTO == null) {
//                return new ResponseEntity<>("Bill detail not found", HttpStatus.NOT_FOUND);
//            }
//            return new ResponseEntity<>(billDetailDTO, HttpStatus.OK);
//        } catch (Exception e) {
//            return new ResponseEntity<>("Error retrieving bill detail", HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }

    @GetMapping("/{userID}/{billId}")
    public ResponseEntity<?> getBillDetailsByBillId(@PathVariable Integer userID,@PathVariable Integer billId) {
        Bill bill=billReponsitory.findById(billId);
        if (bill==null) {
            return new ResponseEntity<>("Bill not found", HttpStatus.NOT_FOUND);
        }
        else{
            Integer userid=bill.getUserID();
            if(userid==null) {
                return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
            }
            else if(!userid.equals(userID)) {
                return new ResponseEntity<>("Bạn không có quyền truy cập đơn hàng này", HttpStatus.FORBIDDEN);
            }
            else {
                List<BillDetailDTO> billDetailDTOs = billDetailRepository.findByBillId(billId);
                if (billDetailDTOs.isEmpty()) {
                    return new ResponseEntity<>("No bill details found for the given bill ID", HttpStatus.NO_CONTENT);
                }
                return new ResponseEntity<>(billDetailDTOs, HttpStatus.OK);
            }
        }

    }

    @PostMapping("")
    public ResponseEntity<?> addBillDetail(@RequestBody BillDetail billDetail) {
        int rows = billDetailRepository.save(billDetail);
        if (rows > 0) {
            return new ResponseEntity<>("Bill detail added successfully", HttpStatus.CREATED);
        }
        return new ResponseEntity<>("Failed to add bill detail", HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateBillDetail(@PathVariable Integer id, @RequestBody BillDetail billDetail) {
        billDetail.setBillDetailId(id);
        int rows = billDetailRepository.update(billDetail);
        if (rows > 0) {
            return new ResponseEntity<>("Bill detail updated successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>("Failed to update bill detail", HttpStatus.BAD_REQUEST);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBillDetail(@PathVariable Integer id) {
        int rows = billDetailRepository.delete(id);
        if (rows > 0) {
            return new ResponseEntity<>("Bill detail deleted successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>("Failed to delete bill detail", HttpStatus.BAD_REQUEST);
    }
}
