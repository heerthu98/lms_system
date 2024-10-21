package com.lms.course_service.service.implementation;

import com.lms.course_service.dto.CourseDto;
import com.lms.course_service.entity.Course;
import com.lms.course_service.exceptions.CourseAlreadyExistException;
import com.lms.course_service.exceptions.CourseNotFoundException;
import com.lms.course_service.exceptions.CourseServiceException;
import com.lms.course_service.mapper.CourseMapper;
import com.lms.course_service.repository.CourseRepo;
import com.lms.course_service.service.ICourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class CourseServiceImpl implements ICourseService {

    @Autowired
    private CourseRepo courseRepo;
    @Override
    public void createCourse(CourseDto courseDto) {
        Course course = CourseMapper.mapToCourseEntity(courseDto, new Course());
        Optional<Course> existingCourse = courseRepo.findByCourseName(course.getCourseName());
        if(existingCourse.isPresent()){
            throw new CourseAlreadyExistException(course.getCourseName());
        }
        course.setCreatedAt(LocalDateTime.now());
        course.setCreatedBy("Admin_Heer");
        courseRepo.save(course);
    }

    @Override
    public CourseDto getCourseById(Long courseId) {
        Optional<Course> course = courseRepo.findByCourseId(courseId);
        if(course.isPresent()) {
            CourseDto courseDto = CourseMapper.mapToCourseDto(course.get(), new CourseDto());
            courseDto.setCreatedAt(course.get().getCreatedAt());
            courseDto.setCreatedBy(course.get().getCreatedBy());
            courseDto.setUpdatedAt(course.get().getUpdatedAt());
            return courseDto;
        }
        else
            throw new CourseNotFoundException("" + courseId);
    }

    @Override
    public Queue<CourseDto> getAllCourse() {
        try {
            List<CourseDto> sortedCourses = courseRepo.findAll().stream()
                    .sorted(Comparator.comparing(Course::getCreatedAt))
                    .map(course -> {
                        CourseDto courseDto = CourseMapper.mapToCourseDto(course, new CourseDto());
                        courseDto.setCourseId(course.getCourseId());
                        courseDto.setCreatedAt(course.getCreatedAt());
                        courseDto.setCreatedBy(course.getCreatedBy());
                        courseDto.setUpdatedAt(course.getUpdatedAt());
                        courseDto.setUpdatedBy(course.getUpdatedBy());
                        return courseDto;
                    })
                    .collect(Collectors.toList());

            Queue<CourseDto> courseQueue = new LinkedList<>(sortedCourses);
            return courseQueue;
        } catch (Exception e) {
            throw new CourseServiceException(e.getMessage());
        }
    }

    @Override
    public CourseDto updateCourse(CourseDto courseDto, Long courseId) {
        Course course = courseRepo.findById(courseId)
                .orElseThrow(() -> new CourseNotFoundException("" + courseId));

        Optional<Course> existingCourse = courseRepo.findByCourseName(courseDto.getCourseName());
        if(existingCourse.isPresent() ){
            throw new CourseAlreadyExistException(courseDto.getCourseName());
        }

        course.setCourseName(courseDto.getCourseName());
        course.setCourseDescription(courseDto.getCourseDescription());
        course.setCourseDuration(courseDto.getCourseDuration());
        course.setUpdatedAt(LocalDateTime.now());
        course.setUpdatedBy("Admin_Heer");
        courseRepo.save(course);
        return courseDto;
    }


    @Override
    public void deleteCourse(Long courseId) {
        Course course = courseRepo.findById(courseId)
                .orElseThrow(() -> new CourseNotFoundException("" + courseId));
        courseRepo.deleteById(course.getCourseId());
    }
}
