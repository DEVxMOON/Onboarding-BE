package com.hr.onboardingjava.auth.repository;

import com.hr.onboardingjava.auth.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long>{
    User findByUsername(String username);
}
