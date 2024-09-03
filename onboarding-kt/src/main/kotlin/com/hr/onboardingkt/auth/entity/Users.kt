package com.hr.onboardingkt.auth.entity

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
class Users(
    var username: String,
    var password: String,
    var nickname: String
){
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
}