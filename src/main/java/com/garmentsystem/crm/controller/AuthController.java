package com.garmentsystem.crm.controller;

import com.garmentsystem.crm.dto.RegisterRequest;
import com.garmentsystem.crm.dto.UserResponse;
import com.garmentsystem.crm.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @PostMapping("/register")
    public UserResponse register(@Valid @RequestBody RegisterRequest request) {
        return userService.register(request);
    }
}