package com.ra.controller;

import com.ra.entity.User;
import com.ra.service.UserService;
import com.ra.util.exception.UserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService ;

    @GetMapping("")
    public ResponseEntity<List<User>> getAll() {
        List<User> list = userService.findAll();
        return new ResponseEntity<>(list, HttpStatus.OK) ;
    }

    @PostMapping("")
    public ResponseEntity<User> create(@RequestBody User user) throws UserException {
        User newUser  = userService.create(user)  ;
        return new ResponseEntity<>(newUser, HttpStatus.CREATED) ;
    }
}
