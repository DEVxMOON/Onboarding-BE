package com.hr.onboardingjava.auth.repository;

import com.hr.onboardingjava.auth.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users,Long>{
    Users findByUsername(String username);
}
