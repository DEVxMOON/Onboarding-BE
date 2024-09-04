package com.hr.onboardingjava.auth.dto;

import com.hr.onboardingjava.auth.entity.User;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @Builder
public class SignUpResponse {
    private String username;
    private String nickname;

    public static SignUpResponse from(User user) {
        return SignUpResponse.builder()
                .username(user.getUsername())
                .nickname(user.getNickname())
                .build();
    }
}