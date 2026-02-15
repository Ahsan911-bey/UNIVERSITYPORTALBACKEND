package com.universityportal.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.universityportal.dto.JwtResponse;
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
    public ResponseEntity<JwtResponse> studentLogin(
            @RequestBody StudentLoginRequestDto dto) {

        return ResponseEntity.ok(authService.studentLogin(dto));
    }

    // -------- TEACHER LOGIN --------
    @PostMapping("/teacher/login")
    public ResponseEntity<JwtResponse> teacherLogin(
            @RequestBody SimpleLoginRequestDto dto) {

        return ResponseEntity.ok(authService.teacherLogin(dto));
    }

    // -------- ADMIN LOGIN --------
    @PostMapping("/admin/login")
    public ResponseEntity<JwtResponse> adminLogin(
            @RequestBody SimpleLoginRequestDto dto) {

        return ResponseEntity.ok(authService.adminLogin(dto));
    }
}
