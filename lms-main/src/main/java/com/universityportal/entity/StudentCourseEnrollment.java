package com.universityportal.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "student_course_enrollments")
public class StudentCourseEnrollment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    @OneToOne(mappedBy = "studentCourseEnrollment", cascade = CascadeType.ALL, orphanRemoval = true)
    private Attendance attendance;

    @OneToOne(mappedBy = "studentCourseEnrollment", cascade = CascadeType.ALL, orphanRemoval = true)
    private Marks marks;

    @OneToMany(mappedBy = "studentCourseEnrollment", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<StudentSubmission> assignmentSubmissions = new ArrayList<>();

    public StudentCourseEnrollment() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Attendance getAttendance() {
        return attendance;
    }

    public void setAttendance(Attendance attendance) {
        this.attendance = attendance;
    }

    public Marks getMarks() {
        return marks;
    }

    public void setMarks(Marks marks) {
        this.marks = marks;
    }

    public List<StudentSubmission> getAssignmentSubmissions() {
        return assignmentSubmissions;
    }

    public void setAssignmentSubmissions(List<StudentSubmission> assignmentSubmissions) {
        this.assignmentSubmissions = assignmentSubmissions;
    }
}


