package com.hr.onboardingkt.auth.dto

import com.hr.onboardingkt.auth.entity.Users

data class SignUpResponse(
    val username: String,
    val nickname: String,
){
    companion object{
        fun from(users: Users): SignUpResponse{
            return SignUpResponse(
                username = users.username,
                nickname = users.nickname
            )
        }
    }
}