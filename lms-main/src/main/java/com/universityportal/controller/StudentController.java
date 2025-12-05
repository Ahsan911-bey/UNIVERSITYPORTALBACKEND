package com.universityportal.controller;

import com.universityportal.dto.*;
import com.universityportal.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/{studentId}")
    public ResponseEntity<StudentDto> getStudent(@PathVariable Long studentId) {
        return ResponseEntity.ok(studentService.getStudent(studentId));
    }

    @GetMapping("/{studentId}/courses")
    public ResponseEntity<List<CourseDto>> getCourses(@PathVariable Long studentId) {
        return ResponseEntity.ok(studentService.getStudentCourses(studentId));
    }

    @GetMapping("/{studentId}/marks")
    public ResponseEntity<List<StudentCourseMarksDto>> getMarks(@PathVariable Long studentId) {
        return ResponseEntity.ok(studentService.getStudentMarks(studentId));
    }

    @GetMapping("/{studentId}/attendance")
    public ResponseEntity<List<StudentCourseAttendanceSummaryDto>> getAttendance(@PathVariable Long studentId) {
        return ResponseEntity.ok(studentService.getStudentAttendance(studentId));
    }

    @PostMapping("/assignment/submit")
    public ResponseEntity<Void> submitAssignment(@Valid @RequestBody StudentSubmissionDto dto) {
        studentService.submitAssignment(dto);
        return ResponseEntity.ok().build();
    }

    // DEPRECATED: Assignments are now included in GET /student/{id}/courses
    // @GetMapping("/{studentId}/assignments")
    // public ResponseEntity<List<StudentAssignmentStatusDto>> getAssignments(@PathVariable Long studentId) {
    //     return ResponseEntity.ok(studentService.getStudentAssignments(studentId));
    // }

}


