package com.universityportal.controller;

import com.universityportal.dto.*;
import com.universityportal.service.AdminService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping("/student")
    public ResponseEntity<StudentDto> createStudent(@Valid @RequestBody StudentDto dto) {
        return new ResponseEntity<>(adminService.createStudent(dto), HttpStatus.CREATED);
    }

    @PostMapping("/teacher")
    public ResponseEntity<TeacherDto> createTeacher(@Valid @RequestBody TeacherDto dto) {
        return new ResponseEntity<>(adminService.createTeacher(dto), HttpStatus.CREATED);
    }

    @PostMapping("/course")
    public ResponseEntity<CourseDto> createCourse(@Valid @RequestBody CourseDto dto) {
        return new ResponseEntity<>(adminService.createCourse(dto), HttpStatus.CREATED);
    }

    @PostMapping("/batch/assign")
    public ResponseEntity<List<StudentDto>> assignBatch(@RequestBody BatchAssignmentRequest request) {
        return ResponseEntity.ok(adminService.assignStudentsToBatch(request));
    }

    @PostMapping("/course/assign-teacher")
    public ResponseEntity<CourseDto> assignTeacher(@RequestBody CourseTeacherAssignmentRequest request) {
        return ResponseEntity.ok(adminService.assignTeacherToCourse(request));
    }

    @PostMapping("/course/assign-students")
    public ResponseEntity<CourseDto> assignStudents(@RequestBody CourseStudentsAssignmentRequest request) {
        return ResponseEntity.ok(adminService.assignStudentsToCourse(request));
    }

    // Get students of a course within a batch (for admin portal)
    @GetMapping("/courses/{courseCode}/{batchName}")
    public ResponseEntity<List<SimpleStudentDto>> getStudentsByCourseAndBatch(@PathVariable String courseCode,
                                                                              @PathVariable String batchName) {
        return ResponseEntity.ok(adminService.getStudentsByCourseAndBatch(courseCode, batchName));
    }

    // Get all teachers
    @GetMapping("/teachers")
    public ResponseEntity<List<TeacherDto>> getAllTeachers() {
        return ResponseEntity.ok(adminService.getAllTeachers());
    }
}


