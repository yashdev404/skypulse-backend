package com.yash.skypulse_backend.dto;

import lombok.Data;

@Data
public class RegisterRequest {
    private String username;
    private String email;
    private String password;
    private String dateOfBirth;
    private String bio;
}