package com.lms.enrollment_service.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(description = "Enrollment Response DTO")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EnrollmentsByUserId {

    @Schema(description = "Enrollment ID", example = "1")
    private  Long enrollmentId;

    @Schema( description = "Course ID", example = "1")
    private Long courseId;

    @Schema(description = "Course Name", example = "java")
    private String courseName;

    @Schema(description = "Course Description", example = "Java course for beginners. Learn Java basics from scratch")
    private String courseDescription;

    @Schema(description = "Course Duration", example = "3 months")
    private String courseDuration;
}
