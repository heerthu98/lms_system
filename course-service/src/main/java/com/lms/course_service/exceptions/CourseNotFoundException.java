package com.lms.course_service.exceptions;

public class CourseNotFoundException extends RuntimeException {

    public CourseNotFoundException(String courseName) {
        super(courseName + " not found");
    }
}
