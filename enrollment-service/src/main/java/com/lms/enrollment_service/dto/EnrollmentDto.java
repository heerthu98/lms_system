package com.lms.enrollment_service.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(description = "Enrollment DTO")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EnrollmentDto {

    @Schema(description = "User ID", example = "1")
    private Long userId;

    @Schema(description = "Course ID", example = "1")
    private Long courseId;

}