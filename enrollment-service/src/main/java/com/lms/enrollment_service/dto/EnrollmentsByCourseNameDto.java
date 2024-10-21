package com.lms.enrollment_service.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(description = "Enrollments By Course Name DTO")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EnrollmentsByCourseNameDto {

    @Schema(description = "Enrollment ID", example = "1")
    private  Long enrollmentId;



}
