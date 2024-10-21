package com.lms.course_service.repository;

import com.lms.course_service.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CourseRepo extends JpaRepository<Course, Long> {

    Optional<Course> findByCourseId(Long courseId);

    Optional<Course> findByCourseName(String courseName);

}
