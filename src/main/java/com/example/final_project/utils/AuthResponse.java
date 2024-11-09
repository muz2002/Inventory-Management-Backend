package com.example.final_project.utils;

import com.example.final_project.entity.Country;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthResponse {
    private String accessToken;
    private String refreshToken;
    private String name;
    private Integer userId;
    private String username;
    private String email;
    private Country country;
}