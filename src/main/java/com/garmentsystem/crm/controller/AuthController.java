package com.garmentsystem.crm.controller;

import com.garmentsystem.crm.dto.LoginRequest;
import com.garmentsystem.crm.dto.LoginResponse;
import com.garmentsystem.crm.dto.RegisterRequest;
import com.garmentsystem.crm.dto.UserResponse;
import com.garmentsystem.crm.service.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @PostMapping("/register")
    @SecurityRequirement(name = "bearerAuth")
    public UserResponse register(@Valid @RequestBody RegisterRequest request) {
        return userService.register(request);
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request) {
        return userService.login(request);
    }
}