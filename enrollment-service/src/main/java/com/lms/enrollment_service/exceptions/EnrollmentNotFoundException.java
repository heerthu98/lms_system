package com.lms.enrollment_service.exceptions;

public class EnrollmentNotFoundException  extends  RuntimeException {
    public EnrollmentNotFoundException(String enrollment) {
        super("Enrollment with id " + enrollment + " does not exist");
    }
}
