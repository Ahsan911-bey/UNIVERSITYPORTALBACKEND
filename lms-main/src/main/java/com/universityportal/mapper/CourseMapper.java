package com.universityportal.mapper;

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
        // studentIds can be derived from enrollments if needed; keep empty here for simplicity
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


