package com.lms.course_service.mapper;

import com.lms.course_service.dto.CourseDto;
import com.lms.course_service.entity.Course;

public class CourseMapper {
    public static CourseDto mapToCourseDto(Course course, CourseDto courseDto) {
        courseDto.setCourseName(course.getCourseName());
        courseDto.setCourseDescription(course.getCourseDescription());
        courseDto.setCourseDuration(course.getCourseDuration());

        return courseDto;
    }

    public static Course mapToCourseEntity(CourseDto courseDto, Course course) {
        course.setCourseName(courseDto.getCourseName());
        course.setCourseDescription(courseDto.getCourseDescription());
        course.setCourseDuration(courseDto.getCourseDuration());

        return course;
    }


}
