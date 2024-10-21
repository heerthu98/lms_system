package com.lms.enrollment_service.mapper;

import com.lms.enrollment_service.dto.CourseDto;
import com.lms.enrollment_service.dto.EnrollmentDto;
import com.lms.enrollment_service.dto.EnrollmentsByUserId;
import com.lms.enrollment_service.entity.Enrollment;

public class EnrollmentMapper {

    public static EnrollmentDto mapToEnrollmentDto(Enrollment enrollment, EnrollmentDto enrollmentDto) {
        enrollmentDto.setCourseId(enrollment.getCourseId());
        enrollmentDto.setUserId(enrollment.getUserId());
        return enrollmentDto;
    }

    public static Enrollment mapToEnrollmentEntity(EnrollmentDto enrollmentDto, Enrollment enrollment) {
        enrollment.setCourseId(enrollmentDto.getCourseId());
        enrollment.setUserId(enrollmentDto.getUserId());
        return enrollment;
    }

    public static EnrollmentsByUserId mapToEnrollmentsByUserId(Enrollment enrollment, CourseDto courseDto) {
        EnrollmentsByUserId enrollmentsByUserId = new EnrollmentsByUserId();
        enrollmentsByUserId.setEnrollmentId(enrollment.getEnrollmentId());
        enrollmentsByUserId.setCourseName(courseDto.getCourseName());
        enrollmentsByUserId.setCourseDuration(courseDto.getCourseDuration());
        enrollmentsByUserId.setCourseDescription(courseDto.getCourseDescription());
        return enrollmentsByUserId;
    }
}
