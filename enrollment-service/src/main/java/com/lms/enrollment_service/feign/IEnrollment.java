package com.lms.enrollment_service.feign;

import com.lms.enrollment_service.dto.CourseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Queue;

@FeignClient("COURSE-SERVICE")
public interface IEnrollment {
    @GetMapping("/api/v2/course/getById")
    ResponseEntity<CourseDto> getCourseById(@RequestParam("courseId") Long courseId);

}
