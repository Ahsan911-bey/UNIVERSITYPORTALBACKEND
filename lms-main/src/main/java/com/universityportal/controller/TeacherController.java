package com.universityportal.controller;

import com.universityportal.dto.*;
import com.universityportal.service.TeacherService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teacher")
public class TeacherController {

    private final TeacherService teacherService;

    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @GetMapping("/{teacherId}/courses")
    public ResponseEntity<List<CourseDto>> getCourses(@PathVariable Long teacherId) {
        return ResponseEntity.ok(teacherService.getCoursesForTeacher(teacherId));
    }

    // Get students of a course within a batch (for teacher portal)
    @GetMapping("/courses/{courseCode}/{batchName}")
    public ResponseEntity<List<SimpleStudentDto>> getStudentsByCourseAndBatch(@PathVariable String courseCode,
                                                                              @PathVariable String batchName) {
        return ResponseEntity.ok(teacherService.getStudentsByCourseAndBatch(courseCode, batchName));
    }

    // New attendance endpoint: mark attendance for all students of a course at once
    @PostMapping("/course/{courseId}/attendance")
    public ResponseEntity<Void> markCourseAttendance(@PathVariable Long courseId,
                                                     @RequestBody List<StudentAttendanceUpdateDto> updates) {
        teacherService.markAttendanceForCourse(courseId, updates);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/announcement")
    public ResponseEntity<AnnouncementDto> createAnnouncement(@Valid @RequestBody AnnouncementDto dto) {
        return new ResponseEntity<>(teacherService.createAnnouncement(dto), HttpStatus.CREATED);
    }

    @PostMapping("/assignment")
    public ResponseEntity<AssignmentDto> createAssignment(@Valid @RequestBody AssignmentDto dto) {
        return new ResponseEntity<>(teacherService.createAssignment(dto), HttpStatus.CREATED);
    }

    @PostMapping("/marks")
    public ResponseEntity<MarksDto> recordMarks(@Valid @RequestBody MarksDto dto) {
        return new ResponseEntity<>(teacherService.recordMarks(dto), HttpStatus.CREATED);
    }
}


