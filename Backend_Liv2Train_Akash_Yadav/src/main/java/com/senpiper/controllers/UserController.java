package com.senpiper.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.senpiper.dtos.UserDto;
import com.senpiper.model.User;
import com.senpiper.services.UserService;

import jakarta.validation.Valid;



@RestController
@RequestMapping("/api/senpiper") 
public class UserController {

    @Autowired private UserService userService;

    
    @PostMapping("/register-user")
    public ResponseEntity<User> register(@Valid @RequestBody UserDto user) {
    	
        User registeredCustomer = userService.registerUser(user);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(registeredCustomer);
    }
    
}
