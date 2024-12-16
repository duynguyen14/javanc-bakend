package com.example.book.controllers;

import com.example.book.DTO.LoginRequest;
import com.example.book.DTO.RegisterRequest;
import com.example.book.DTO.UserDTO;
import com.example.book.models.Account;
import com.example.book.models.User;
import com.example.book.repository.AccountReponsitory;
import com.example.book.repository.UserReponsitory;
import org.springframework.data.relational.core.sql.In;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/user")
@CrossOrigin("*")
public class userController {
    private final UserReponsitory userReponsitory;
    private final AccountReponsitory accountReponsitory;
    public userController(UserReponsitory userReponsitory,AccountReponsitory accountReponsitory) {
        this.userReponsitory = userReponsitory;
        this.accountReponsitory = accountReponsitory;
    }
    @GetMapping("")
    public ResponseEntity<List<User>> findAll() {
        List<User> users = userReponsitory.findAll();
        if (users.isEmpty()) {
            // Nếu danh sách rỗng, trả về HTTP 204 (No Content)
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Integer id) {
        Optional<User> user = Optional.ofNullable(userReponsitory.findById(id));
        if (user.isPresent()) {
            // Trả về HTTP 200 (OK) nếu tìm thấy user
            return new ResponseEntity<>(user.get(), HttpStatus.OK);
        } else {
            // Trả về HTTP 404 (Not Found) nếu không tìm thấy user
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        Optional<User> user1= Optional.ofNullable(userReponsitory.findByEmail(loginRequest.getEmail()));
            if (user1.isPresent()&&user1.get().getPassword().equals(loginRequest.getPassword())) {
                Account account = accountReponsitory.findByUserId(user1.get().getId());
                UserDTO userDTO = new UserDTO(user1.get(),account);
                return new ResponseEntity<>(userDTO, HttpStatus.OK);
            }
            else{
                return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
            }
    }
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest registerRequest) {
        Optional<User> user1=Optional.ofNullable(userReponsitory.findByEmail(registerRequest.getEmail()));
        if(user1.isPresent()){
            return new ResponseEntity<>("User already exists", HttpStatus.BAD_REQUEST);
        }
        else{
            User user = new User();
            user.setEmail(registerRequest.getEmail());
            user.setPassword(registerRequest.getPassword());
            user.setUserName(registerRequest.getUserName());
            userReponsitory.saveUser(user);
            System.out.println("user id"+user.getId());
            Account account = new Account();
            account.setUserId(user.getId());
            account.setRole("user");
            accountReponsitory.save(account);
            return new ResponseEntity<>("Registration successful",HttpStatus.CREATED);
        }
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody User user) {
        user.setId(id);
        int row=userReponsitory.updateUser(user);
        if(row==1){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
//    @PatchMapping("/update/{id}")

}
