package com.lms.course_service.service;

import com.lms.course_service.dto.CourseDto;

import java.util.Queue;

public interface IPdfGeneratorService {
    byte[] generateCoursePdf(Queue<CourseDto> courses);
}