

package com.lms.enrollment_service.service.implementation;
import com.lms.enrollment_service.dto.CourseDto;
import com.lms.enrollment_service.dto.EnrollmentDto;

import com.lms.enrollment_service.dto.EnrollmentsByCourseNameDto;
import com.lms.enrollment_service.dto.EnrollmentsByUserId;
import com.lms.enrollment_service.entity.Enrollment;
import com.lms.enrollment_service.exceptions.EnrollmentNotFoundException;
import com.lms.enrollment_service.exceptions.UserAlreadyEnrolled;
import com.lms.enrollment_service.feign.IEnrollment;
import com.lms.enrollment_service.mapper.EnrollmentMapper;
import com.lms.enrollment_service.repository.EnrollmentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import service.IEnrollmentService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EnrollmentServiceImpl implements IEnrollmentService {


    @Autowired
    private IEnrollment iEnrollment;

    @Autowired
    private EnrollmentRepo enrollmentRepo;

    @Override
    public EnrollmentDto enrollInCourse(EnrollmentDto enrollmentDto) {
        Enrollment enrollment = EnrollmentMapper.mapToEnrollmentEntity(enrollmentDto, new Enrollment());
        Optional<Enrollment> existingEnrollment = enrollmentRepo.findByUserIdAndCourseId(enrollmentDto.getUserId(), enrollmentDto.getCourseId());
        if(existingEnrollment.isPresent()){
            throw new UserAlreadyEnrolled( enrollmentDto.getUserId().toString() , enrollmentDto.getCourseId().toString());
        }
        enrollment.setCreatedAt(LocalDateTime.now());
        enrollment.setCreatedBy("User_Heer");
        enrollmentRepo.save(enrollment);
        return enrollmentDto;

    }

    @Override
    public void cancelEnrollment(Long enrollmentId) {
        Optional<Enrollment> enrollmentOptional = enrollmentRepo.findById(enrollmentId);
        if (enrollmentOptional.isPresent()) {
            Enrollment enrollment = enrollmentOptional.get();
            enrollmentRepo.delete(enrollment);
        } else {
            throw new EnrollmentNotFoundException(enrollmentId.toString());
        }
    }

    @Override
    public List<EnrollmentsByUserId> getEnrollmentsByUserId(Long userId) {
        List<Enrollment> enrollments = enrollmentRepo.findByUserId(userId);
        List<EnrollmentsByUserId> enrollmentsByUserId = enrollments.stream().map(enrollment -> {
            try {
                Long courseId = enrollment.getCourseId();
                if(courseId != null) {
                    ResponseEntity<CourseDto> courseResponseEntity = iEnrollment.getCourseById(courseId);
                    CourseDto courseDto = courseResponseEntity.getBody();
                    return EnrollmentMapper.mapToEnrollmentsByUserId(enrollment, courseDto);
                } else {
                    throw new EnrollmentNotFoundException(enrollment.getEnrollmentId().toString());
                }
            } catch (Exception e) {
                throw new EnrollmentNotFoundException(enrollment.getEnrollmentId().toString());
            }
        }).collect(Collectors.toList());
        return enrollmentsByUserId;
    }

}
