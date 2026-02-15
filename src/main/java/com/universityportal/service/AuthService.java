package com.universityportal.service;

import com.universityportal.dto.JwtResponse;
import com.universityportal.dto.SimpleLoginRequestDto;
import com.universityportal.dto.StudentLoginRequestDto;
import com.universityportal.entity.Admin;
import com.universityportal.entity.Student;
import com.universityportal.entity.Teacher;
import com.universityportal.exception.InvalidCredentialsException;
import com.universityportal.repository.AdminRepository;
import com.universityportal.repository.StudentRepository;
import com.universityportal.repository.TeacherRepository;
import com.universityportal.util.JwtUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;
    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;

    public AuthService(StudentRepository studentRepository,
            TeacherRepository teacherRepository,
            AdminRepository adminRepository,
            PasswordEncoder passwordEncoder,
            JwtUtils jwtUtils) {
        this.studentRepository = studentRepository;
        this.teacherRepository = teacherRepository;
        this.adminRepository = adminRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtils = jwtUtils;
    }

    // ---------- STUDENT LOGIN ----------
    public JwtResponse studentLogin(StudentLoginRequestDto dto) {

        Student student = studentRepository.findById(dto.getId())
                .orElseThrow(() -> new InvalidCredentialsException("Student ID is incorrect"));

        if (!student.getRegNo().equals(dto.getRegNo())) {
            throw new InvalidCredentialsException("Student Registration Number is incorrect");
        }

        if (!passwordEncoder.matches(dto.getPassword(), student.getPassword())) {
            throw new InvalidCredentialsException("Student password is incorrect");
        }

        // Generate Token
        String token = jwtUtils.generateJwtTokenFromUsername(student.getEmailAddress());
        JwtResponse response = new JwtResponse(token, student.getEmailAddress());
        response.setRole("STUDENT");
        return response;
    }

    // ---------- TEACHER LOGIN ----------
    public JwtResponse teacherLogin(SimpleLoginRequestDto dto) {

        Teacher teacher = teacherRepository.findById(dto.getId())
                .orElseThrow(() -> new InvalidCredentialsException("Teacher ID is incorrect"));

        if (!passwordEncoder.matches(dto.getPassword(), teacher.getPassword())) {
            throw new InvalidCredentialsException("Teacher password is incorrect");
        }

        String token = jwtUtils.generateJwtTokenFromUsername(teacher.getEmailAddress());
        JwtResponse response = new JwtResponse(token, teacher.getEmailAddress());
        response.setRole("TEACHER");
        return response;
    }

    // ---------- ADMIN LOGIN ----------
    public JwtResponse adminLogin(SimpleLoginRequestDto dto) {

        Admin admin = adminRepository.findById(dto.getId())
                .orElseThrow(() -> new InvalidCredentialsException("Admin ID is incorrect"));

        if (!passwordEncoder.matches(dto.getPassword(), admin.getPassword())) {
            throw new InvalidCredentialsException("Admin password is incorrect");
        }

        String token = jwtUtils.generateJwtTokenFromUsername(admin.getEmailAddress());
        JwtResponse response = new JwtResponse(token, admin.getEmailAddress());
        response.setRole("ADMIN");
        return response;
    }
}
