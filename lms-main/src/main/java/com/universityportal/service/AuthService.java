package com.universityportal.service;

import org.springframework.stereotype.Service;

import com.universityportal.dto.SimpleLoginRequestDto;
import com.universityportal.dto.StudentLoginRequestDto;
import com.universityportal.entity.Admin;
import com.universityportal.entity.Student;
import com.universityportal.entity.Teacher;
import com.universityportal.exception.InvalidCredentialsException;
import com.universityportal.repository.AdminRepository;
import com.universityportal.repository.StudentRepository;
import com.universityportal.repository.TeacherRepository;

@Service
public class AuthService {

    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;
    private final AdminRepository adminRepository;

    public AuthService(StudentRepository studentRepository,
                       TeacherRepository teacherRepository,
                       AdminRepository adminRepository) {
        this.studentRepository = studentRepository;
        this.teacherRepository = teacherRepository;
        this.adminRepository = adminRepository;
    }

    // ---------- STUDENT LOGIN ----------
    public void studentLogin(StudentLoginRequestDto dto) {

        Student student = studentRepository.findById(dto.getId())
                .orElseThrow(() -> new InvalidCredentialsException("Student ID is incorrect"));

        if (!student.getRegNo().equals(dto.getRegNo())) {
            throw new InvalidCredentialsException("Student Registration Number is incorrect");
        }

        if (!student.getPassword().equals(dto.getPassword())) {
            throw new InvalidCredentialsException("Student password is incorrect");
        }
    }

    // ---------- TEACHER LOGIN ----------
    public void teacherLogin(SimpleLoginRequestDto dto) {

        Teacher teacher = teacherRepository.findById(dto.getId())
                .orElseThrow(() -> new InvalidCredentialsException("Teacher ID is incorrect"));

        if (!teacher.getPassword().equals(dto.getPassword())) {
            throw new InvalidCredentialsException("Teacher password is incorrect");
        }
    }

    // ---------- ADMIN LOGIN ----------
    public void adminLogin(SimpleLoginRequestDto dto) {

        Admin admin = adminRepository.findById(dto.getId())
                .orElseThrow(() -> new InvalidCredentialsException("Admin ID is incorrect"));

        if (!admin.getPassword().equals(dto.getPassword())) {
            throw new InvalidCredentialsException("Admin password is incorrect");
        }
    }
}
