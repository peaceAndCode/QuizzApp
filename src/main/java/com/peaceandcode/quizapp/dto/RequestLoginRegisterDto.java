package com.peaceandcode.quizapp.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class RequestLoginRegisterDto {
    @NotBlank
    @Pattern(regexp = "^[a-zA-Z][a-zA-Z0-9_-]{4,20}$", message = "Invalid username")
    private String username;
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[!@#$%^&*(),.?\":{}|<>]).{8,}$",message = "invalid password")
    private String password;
    private String confirmPassword = null;
}
