package com.example.book.controllers;

import com.example.book.models.Bill;
import com.example.book.repository.BillReponsitory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/bill")
@CrossOrigin("*")
public class billController {
    private final BillReponsitory billReponsitory;

    public billController(BillReponsitory billReponsitory) {
        this.billReponsitory = billReponsitory;
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

    @PostMapping("")
    public ResponseEntity<?> addBill(@RequestBody Bill bill) {
        int rows = billReponsitory.save(bill);
        if (rows > 0) {
            return new ResponseEntity<>("Bill added successfully", HttpStatus.CREATED);
        }
        return new ResponseEntity<>("Failed to add bill", HttpStatus.BAD_REQUEST);
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
        int rows = billReponsitory.delete(id);
        if (rows > 0) {
            return new ResponseEntity<>("Bill deleted successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>("Failed to delete bill", HttpStatus.BAD_REQUEST);
    }
}
