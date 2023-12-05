package com.example.cowork.controller;

import com.example.cowork.service.UserService;
import com.example.cowork.payload.request.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        return userService.login(loginRequest.getUser_email(), loginRequest.getUser_pw());
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody SignupRequest signupRequest) {
        return userService.signup(signupRequest.getUser_email(), signupRequest.getUser_name(), signupRequest.getUser_pw());
    }
}

