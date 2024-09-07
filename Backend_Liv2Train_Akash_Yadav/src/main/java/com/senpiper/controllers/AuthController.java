package com.senpiper.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.senpiper.dtos.LoginResponseDto;
import com.senpiper.dtos.LoginUserDto;
import com.senpiper.model.User;
import com.senpiper.services.JwtService;
import com.senpiper.services.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/senpiper/auth") 
public class AuthController {
	@Autowired private UserService userService;
    @Autowired private JwtService jwtService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> authenticate(@RequestBody @Valid LoginUserDto loginUserDto) {
    	
        User authenticatedUser = userService.loginUser(loginUserDto);
        String jwtToken = jwtService.generateToken(authenticatedUser);

        LoginResponseDto loginResponse = new LoginResponseDto();
        loginResponse.setToken(jwtToken);
        loginResponse.setExpiresIn(jwtService.getExpirationTime());

        return ResponseEntity.ok(loginResponse);
    }
	
	
}
