package com.hr.onboardingkt.auth.repository

import com.hr.onboardingkt.auth.entity.Users
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<Users, Int>{
    fun findByUsername(username: String): Users?
}