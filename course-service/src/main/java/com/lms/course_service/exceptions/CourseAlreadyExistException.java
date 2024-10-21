package com.lms.course_service.exceptions;

public class CourseAlreadyExistException extends RuntimeException {

    public CourseAlreadyExistException(String courseName) {
        super(courseName + " already exists");
    }
}
