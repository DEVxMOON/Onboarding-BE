package com.hr.onboardingjava.auth.controller;

import com.hr.onboardingjava.auth.dto.LoginRequest;
import com.hr.onboardingjava.auth.dto.LoginResponse;
import com.hr.onboardingjava.auth.dto.SignUpRequest;
import com.hr.onboardingjava.auth.dto.SignUpResponse;
import com.hr.onboardingjava.auth.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    private final UserService userService;

    // 생성자 주입
    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        LoginResponse response = userService.login(loginRequest);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/signup")
    public ResponseEntity<SignUpResponse> signUp(@RequestBody SignUpRequest signUpRequest) {
        SignUpResponse response = userService.signUp(signUpRequest);
        return ResponseEntity.ok(response);
    }
}