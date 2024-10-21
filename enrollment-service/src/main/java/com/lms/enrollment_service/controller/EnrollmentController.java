package com.lms.enrollment_service.controller;

import com.lms.enrollment_service.constants.EnrollmentConstants;
import com.lms.enrollment_service.dto.EnrollmentDto;
import com.lms.enrollment_service.dto.EnrollmentsByUserId;
import com.lms.enrollment_service.dto.ErrorResponseDto;
import com.lms.enrollment_service.dto.ResponseDto;
import com.lms.enrollment_service.feign.IEnrollment;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.IEnrollmentService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/enrollment")
@Tag(name = "Enrollment Controller", description = "Operations pertaining to Course Enrollment")
public class EnrollmentController {

    @Autowired
    private IEnrollmentService iEnrollmentService;

    @Autowired
    private IEnrollment iEnrollment;

    @Operation(summary = "Enroll in Course")
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Enrollment created", content = @Content(
                schema = @Schema(implementation = EnrollmentDto.class)
        )),
        @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(
                schema = @Schema(implementation = ErrorResponseDto.class)
        ))
    })
    @PostMapping("/enroll")
    public ResponseEntity<EnrollmentDto> enrollInCourse(@RequestBody EnrollmentDto enrollmentDto) {
        EnrollmentDto createdEnrollment = iEnrollmentService.enrollInCourse(enrollmentDto);
        return ResponseEntity.status(201).body(createdEnrollment);
    }

    @Operation(summary = "Get All Enrollments for a particular user")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Enrollments found", content = @Content(
                schema = @Schema(implementation = EnrollmentsByUserId.class)
        )),
        @ApiResponse(responseCode = "404", description = "No enrollments found", content = @Content(
                schema = @Schema(implementation = ErrorResponseDto.class)
        ))
    })
    @GetMapping("{userId}")
    public ResponseEntity<List<EnrollmentsByUserId>> getEnrollmentsByUserId(@PathVariable Long userId) {
        List<EnrollmentsByUserId> enrollments = iEnrollmentService.getEnrollmentsByUserId(userId);
        return ResponseEntity.status(HttpStatus.OK).body(enrollments);
    }


    @Operation(summary = "Cancel the Enrollment")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Enrollment Cancelled Successfully"),
        @ApiResponse(responseCode = "404", description = "Enrollment not found", content = @Content(
                schema = @Schema(implementation = ErrorResponseDto.class)
        ))
    })

    @DeleteMapping("/cancel/{enrollmentId}")
    public ResponseEntity<ResponseDto> cancelEnrollment(@PathVariable Long enrollmentId) {
        iEnrollmentService.cancelEnrollment(enrollmentId);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(EnrollmentConstants.STATUS_Cancelled_200, EnrollmentConstants.MESSAGE_Cancelled_200));
    }

}

