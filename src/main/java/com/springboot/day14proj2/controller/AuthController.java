package com.springboot.day14proj2.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.day14proj2.dto.AdminLoginRequestDTO;
import com.springboot.day14proj2.dto.AdminLoginResponseDTO;
import com.springboot.day14proj2.service.AdminService;

import lombok.RequiredArgsConstructor;

@RequestMapping("/api/auth")
@RestController
@RequiredArgsConstructor
public class AuthController {
    private final AdminService wardenService;

    @PostMapping
    public ResponseEntity<AdminLoginResponseDTO> login(@RequestBody AdminLoginRequestDTO user) {
        return ResponseEntity.ok(wardenService.login(user));
    }
}
