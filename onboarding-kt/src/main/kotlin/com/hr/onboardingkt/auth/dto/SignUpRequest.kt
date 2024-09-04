package com.hr.onboardingkt.auth.dto

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Pattern
import jakarta.validation.constraints.Size

data class SignUpRequest (
    @field:NotBlank(message = "사용자 이름은 비워둘 수 없습니다")
    val username: String,
    @field:NotBlank(message = "비밀번호는 비워둘 수 없습니다")
    @field:Size(min = 8, message = "비밀번호는 최소 8자 이상이어야 합니다")
    @field:Pattern(
        regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=!]).*$",
        message = "비밀번호는 최소한 하나의 소문자, 하나의 대문자, 하나의 숫자, 하나의 특수 문자를 포함해야 합니다"
    )
    val password: String,
    @field:NotBlank(message = "닉네임은 비워둘 수 없습니다")
    val nickname: String,
)