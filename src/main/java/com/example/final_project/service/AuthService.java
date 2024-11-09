package com.example.final_project.service;

import com.example.final_project.entity.Country;
import com.example.final_project.entity.User;
import com.example.final_project.entity.UserRole;
import com.example.final_project.repository.CountryRepository;
import com.example.final_project.repository.UserRepository;
import com.example.final_project.utils.AuthResponse;
import com.example.final_project.utils.LoginRequest;
import com.example.final_project.utils.RegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final CountryRepository countryRepository;
    private final JwtService jwtService;
    private final RefreshTokenService refreshTokenService;
    private final AuthenticationManager authenticationManager;

    // src/main/java/com/example/final_project/service/AuthService.java
    public AuthResponse register(RegisterRequest registerRequest) {
        Country country;

        if (registerRequest.getCountryId() != null) {
            country = countryRepository.findById(registerRequest.getCountryId())
                    .orElseThrow(() -> new RuntimeException("Country not found"));
        } else if (registerRequest.getCountryName() != null && !registerRequest.getCountryName().isEmpty()) {
            country = new Country();
            country.setCountryName(registerRequest.getCountryName());
            country = countryRepository.save(country);
        } else {
            throw new RuntimeException("Country information is required");
        }

        var user = User.builder()
                .name(registerRequest.getName())
                .email(registerRequest.getEmail())
                .username(registerRequest.getUsername())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .country(country)
                .role(UserRole.USER)
                .build();

        User savedUser = userRepository.save(user);
        var accessToken = jwtService.generateToken(savedUser);
        var refreshToken = refreshTokenService.createRefreshToken(savedUser.getEmail());

        return AuthResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken.getRefreshToken())
                .userId(savedUser.getUserId())
                .name(savedUser.getName())
                .username(savedUser.getActualUsername()) // Use getActualUsername() here
                .email(savedUser.getEmail())
                .country(savedUser.getCountry())
                .build();
    }

    public AuthResponse login(LoginRequest loginRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                )
        );
        var user = userRepository.findByEmail(loginRequest.getEmail()).orElseThrow(() -> new UsernameNotFoundException("User not found!"));
        var accessToken = jwtService.generateToken(user);
        var refreshToken = refreshTokenService.createRefreshToken(loginRequest.getEmail());

        return AuthResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken.getRefreshToken())
                .name(user.getName())
                .userId(user.getUserId())
                .build();
    }
}