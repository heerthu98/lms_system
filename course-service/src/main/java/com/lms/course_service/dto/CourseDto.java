package com.lms.course_service.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Course DTO")
public class CourseDto {

    @Column(name = "course_id")
    @Schema(description = "Course Id", example = "1")
    private Long courseId;

    @Column(name = "course_name")
    @NotEmpty(message = "Course Name cannot be empty")
    @Size(min = 3, message = "Course Name should have at least 3 characters")
    @Schema(description = "Course Name", example = "Java", required = true)
    private String courseName;

    @Column(name = "course_description")
    @NotEmpty(message = "Course Description cannot be empty")
    @Size(min = 10, message = "Course Description should have at least 10 characters")
    @Schema(description = "Course Description", example = "Java course for beginners. Learn Java basics from scratch", required = true)
    private String courseDescription;

    @Column(name = "course_duration")
    @NotEmpty(message = "Course Duration cannot be empty")
    @Schema(description = "Course Duration", example = "3 months", required = true)
    private String courseDuration;

    private LocalDateTime createdAt;
    private String createdBy;
    private LocalDateTime updatedAt;
    private String updatedBy;
}
