package com.hr.onboardingjava.auth.service;

import com.hr.onboardingjava.auth.dto.LoginRequest;
import com.hr.onboardingjava.auth.dto.LoginResponse;
import com.hr.onboardingjava.auth.dto.SignUpRequest;
import com.hr.onboardingjava.auth.dto.SignUpResponse;
import com.hr.onboardingjava.auth.entity.User;
import com.hr.onboardingjava.auth.repository.UserRepository;
import com.hr.onboardingjava.security.jwt.JwtPlugin;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtPlugin jwtPlugin;

    public LoginResponse login(LoginRequest loginRequest) {
        User user = userRepository.findByUsername(loginRequest.getUsername());
        if (user != null && passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            String token = jwtPlugin.generateAccessToken(user.getId().toString(),user.getUsername(),"USER");
            return LoginResponse.builder().token(token).build();
        }
        throw new RuntimeException("Invalid credentials");
    }

    @Transactional
    public SignUpResponse signUp(SignUpRequest signUpRequest) {
        User user = userRepository.findByUsername(signUpRequest.getUsername());
        if (user != null) {
            throw new RuntimeException("Username already exists");
        }

        User newUser = new User();
        newUser.setUsername(signUpRequest.getUsername());
        newUser.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
        newUser.setNickname(signUpRequest.getNickname());

        User savedUser = userRepository.save(newUser);
        return SignUpResponse.from(savedUser);
    }
}