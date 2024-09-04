package com.hr.onboardingjava.auth.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @Builder
public class LoginRequest {
    @NotBlank(message = "사용자 이름은 비워둘 수 없습니다")
    private String username;
    @NotBlank(message = "비밀번호는 비워둘 수 없습니다")
    @Size(min = 8, message = "비밀번호는 최소 8자 이상이어야 합니다")
    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=!]).*$",
            message = "비밀번호는 최소한 하나의 소문자, 하나의 대문자, 하나의 숫자, 하나의 특수 문자를 포함해야 합니다"
    )
    private String password;
}
