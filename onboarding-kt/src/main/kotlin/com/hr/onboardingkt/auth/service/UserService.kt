package com.hr.onboardingkt.auth.service

import com.hr.onboardingkt.auth.dto.LoginRequest
import com.hr.onboardingkt.auth.dto.LoginResponse
import com.hr.onboardingkt.auth.dto.SignUpRequest
import com.hr.onboardingkt.auth.dto.SignUpResponse
import com.hr.onboardingkt.auth.repository.UserRepository
import com.hr.onboardingkt.security.jwt.JwtPlugin
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserService (
    private val userRepository: UserRepository,
    private val jwtPlugin: JwtPlugin,
    private val passwordEncoder: PasswordEncoder
){
    fun login(
        loginRequest: LoginRequest
    ): LoginResponse {
        val user =userRepository.findByUsername(loginRequest.username)
        if(user != null && passwordEncoder.matches(loginRequest.password, user.password)) {
            val token = jwtPlugin.generateAccessToken(user.id.toString(), user.username,"USER")
            return LoginResponse(token)
        }
        throw RuntimeException()
    }

    @Transactional
    fun signUp(
        signUpRequest: SignUpRequest
    ): SignUpResponse {
        val user = userRepository.findByUsername(signUpRequest.username)?:throw RuntimeException()
        if(user.username!= signUpRequest.username) throw RuntimeException("User name is ${signUpRequest.username}")

        user.apply {
            username = signUpRequest.username
            password = passwordEncoder.encode(signUpRequest.password)
            nickname = signUpRequest.nickname
        }

        val savedUser = userRepository.save(user)
        return SignUpResponse.from(savedUser)
    }
}