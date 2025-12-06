package com.universityportal.mapper;

import com.universityportal.dto.AssignmentWithStatusDto;
import com.universityportal.dto.CourseDto;
import com.universityportal.entity.Course;

public class CourseMapper {

    private CourseMapper() {
    }

    public static CourseDto toDto(Course course) {
        if (course == null) {
            return null;
        }
        CourseDto dto = new CourseDto();
        dto.setId(course.getId());
        dto.setCourseNo(course.getCourseNo());
        dto.setCourseName(course.getCourseName());
        dto.setCredits(course.getCredits());
        if (course.getTeacher() != null) {
            dto.setTeacherId(course.getTeacher().getId());
        }
        // Derive studentIds from enrollments
        if (course.getEnrollments() != null) {
            dto.setStudentIds(course.getEnrollments().stream()
                    .filter(enrollment -> enrollment.getStudent() != null && enrollment.getStudent().getId() != null)
                    .map(enrollment -> enrollment.getStudent().getId())
                    .toList());
        }
        if (course.getAnnouncements() != null) {
            dto.setAnnouncements(course.getAnnouncements().stream()
                    .map(AnnouncementMapper::toDto)
                    .toList());
        }
        if (course.getLearningResources() != null) {
            dto.setLearningResources(course.getLearningResources().stream()
                    .map(LearningResourceMapper::toDto)
                    .toList());
        }
        if (course.getAssignments() != null) {
            dto.setAssignments(course.getAssignments().stream()
                    .map(assignment -> {
                        AssignmentWithStatusDto statusDto = new AssignmentWithStatusDto();
                        statusDto.setId(assignment.getId());
                        statusDto.setTitle(assignment.getTitle());
                        statusDto.setDescription(assignment.getDescription());
                        statusDto.setDueDate(assignment.getDueDate());
                        statusDto.setTeacherFileUrl(assignment.getFileUrl());
                        statusDto.setTeacherSubmitted(true);
                        statusDto.setStudentSubmissionFileUrl(null);
                        statusDto.setStatus("pending");
                        return statusDto;
                    })
                    .toList());
        }
        return dto;
    }

    public static Course toEntity(CourseDto dto) {
        if (dto == null) {
            return null;
        }
        Course course = new Course();
        course.setCourseNo(dto.getCourseNo());
        course.setCourseName(dto.getCourseName());
        course.setCredits(dto.getCredits());
        return course;
    }

    public static void copyBasicFields(CourseDto dto, Course course) {
        if (dto == null || course == null) {
            return;
        }
        course.setCourseNo(dto.getCourseNo());
        course.setCourseName(dto.getCourseName());
        course.setCredits(dto.getCredits());
    }
}


