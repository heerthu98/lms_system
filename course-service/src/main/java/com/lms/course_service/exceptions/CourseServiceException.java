package com.lms.course_service.exceptions;

public class CourseServiceException extends RuntimeException {

    public CourseServiceException(String message) {
        super("Error occurred while fetching all courses");
    }
}
