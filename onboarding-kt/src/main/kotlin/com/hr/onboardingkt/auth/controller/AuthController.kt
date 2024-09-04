package com.hr.onboardingkt.auth.controller

import com.hr.onboardingkt.auth.dto.LoginRequest
import com.hr.onboardingkt.auth.dto.LoginResponse
import com.hr.onboardingkt.auth.dto.SignUpRequest
import com.hr.onboardingkt.auth.dto.SignUpResponse
import com.hr.onboardingkt.auth.service.UserService
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class AuthController(
val userService: UserService
) {
    @PostMapping("/login")
    fun login(@Valid @RequestBody loginRequest: LoginRequest): ResponseEntity<LoginResponse> {
        return ResponseEntity.ok(userService.login(loginRequest))
    }

    @PostMapping("/signup")
    fun signUp(@Valid @RequestBody signUpRequest: SignUpRequest): ResponseEntity<SignUpResponse> {
        return ResponseEntity.ok(userService.signUp(signUpRequest))
    }
}