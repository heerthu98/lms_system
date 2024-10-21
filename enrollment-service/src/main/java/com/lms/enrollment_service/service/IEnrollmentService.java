package service;

import com.lms.enrollment_service.dto.EnrollmentDto;
import com.lms.enrollment_service.dto.EnrollmentsByCourseNameDto;
import com.lms.enrollment_service.dto.EnrollmentsByUserId;

import java.util.List;

public interface IEnrollmentService {

    EnrollmentDto enrollInCourse(EnrollmentDto enrollmentDto);

    void cancelEnrollment(Long enrollmentId);

    List<EnrollmentsByUserId> getEnrollmentsByUserId(Long userId);



}
