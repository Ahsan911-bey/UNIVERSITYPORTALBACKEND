package com.universityportal.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.universityportal.dto.LoginResponseDto;
import com.universityportal.dto.SimpleLoginRequestDto;
import com.universityportal.dto.StudentLoginRequestDto;
import com.universityportal.service.AuthService;

@RestController
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    // -------- STUDENT LOGIN --------
    @PostMapping("/student/login")
    public ResponseEntity<LoginResponseDto> studentLogin(
            @RequestBody StudentLoginRequestDto dto) {

        authService.studentLogin(dto);
        return ResponseEntity.ok(new LoginResponseDto("Validated"));
    }

    // -------- TEACHER LOGIN --------
    @PostMapping("/teacher/login")
    public ResponseEntity<LoginResponseDto> teacherLogin(
            @RequestBody SimpleLoginRequestDto dto) {

        authService.teacherLogin(dto);
        return ResponseEntity.ok(new LoginResponseDto("Validated"));
    }

    // -------- ADMIN LOGIN --------
    @PostMapping("/admin/login")
    public ResponseEntity<LoginResponseDto> adminLogin(
            @RequestBody SimpleLoginRequestDto dto) {

        authService.adminLogin(dto);
        return ResponseEntity.ok(new LoginResponseDto("Validated"));
    }
}
