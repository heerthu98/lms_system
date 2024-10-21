package com.lms.enrollment_service.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Enrollment Entity")
public class Enrollment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "enrollment_id")
    private Long enrollmentId;

    @Schema(description = "Course ID", example = "1")
    @Column(name = "course_id")
    private Long courseId;

    @Schema(description = "User ID", example = "1")
    @Column(name = "user_id")
    private Long userId;
}
