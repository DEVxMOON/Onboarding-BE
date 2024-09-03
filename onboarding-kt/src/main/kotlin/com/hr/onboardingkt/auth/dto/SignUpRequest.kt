package com.hr.onboardingkt.auth.dto

data class SignUpRequest (
    val username: String,
    val password: String,
    val nickname: String,
)