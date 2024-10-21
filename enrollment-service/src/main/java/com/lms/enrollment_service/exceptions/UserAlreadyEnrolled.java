package com.lms.enrollment_service.exceptions;

public class UserAlreadyEnrolled  extends RuntimeException{

    public UserAlreadyEnrolled(String Username, String courseName) {
        super("User with id " +Username + " already enrolled in course " + courseName);
    }

}
