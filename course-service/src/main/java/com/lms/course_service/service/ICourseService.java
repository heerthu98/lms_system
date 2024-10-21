package com.lms.course_service.service;

import com.lms.course_service.dto.CourseDto;

import java.util.List;
import java.util.Queue;

public interface ICourseService {

    void createCourse(CourseDto courseDto);

    CourseDto getCourseById(Long courseId);

    Queue<CourseDto> getAllCourse();

    CourseDto updateCourse(CourseDto courseDto, Long courseId);

    void deleteCourse(Long courseId);
}
