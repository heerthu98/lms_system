package com.lms.course_service.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Course extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "course_id")
    private long courseId;

    @Column(name = "course_name")
    @NotEmpty(message = "Course Name cannot be empty")
    @Size(min = 3, message = "Course Name should have at least 3 characters")
    private String courseName;

    @Column(name = "course_description")
    @NotEmpty(message = "Course Description cannot be empty")
    @Size(min = 10, message = "Course Description should have at least 10 characters")
    private String courseDescription;

    @Column(name = "course_duration")
    @NotEmpty(message = "Course Duration cannot be empty")
    private String courseDuration;
}
