package com.lms.course_service.controller;

import com.lms.course_service.constants.CourseConstants;
import com.lms.course_service.dto.CourseDto;
import com.lms.course_service.dto.ResponseDto;
import com.lms.course_service.dto.ErrorResponseDto;
import com.lms.course_service.exceptions.CourseAlreadyExistException;
import com.lms.course_service.exceptions.CourseNotFoundException;
import com.lms.course_service.service.ICourseService;
import com.lms.course_service.service.IPdfGeneratorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Queue;

@RestController
@RequestMapping(value="/api/v2/course", produces = {MediaType.APPLICATION_JSON_VALUE})
@Tag(name = "Course Controller",
    description = "Operations pertaining to Course")
public class CourseController {

    @Autowired
    private ICourseService icourseService;

    @Autowired
    private IPdfGeneratorService ipdfGeneratorService;

    @Operation(
            summary = "Create Course"
    )
    @ApiResponses(
            {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Course created"
                    ),
                    @ApiResponse(
                            responseCode = "409",
                            description = "Course already exist",
                            content = @Content(
                                    schema = @Schema(implementation = ErrorResponseDto.class)
                            )
                    )
            }
    )
    @PostMapping("/create")
    public ResponseEntity<ResponseDto> addCourse(@Valid @RequestBody CourseDto courseDto) {
        try {
            icourseService.createCourse(courseDto);
            return  ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDto(CourseConstants.STATUS_201, CourseConstants.MESSAGE_201));
        } catch (CourseAlreadyExistException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ResponseDto(CourseConstants.STATUS_409, CourseConstants.MESSAGE_409));
        }
    }

    @Operation(
            summary = "Get Course By Name")
    @ApiResponses(
            {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully return course"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Course not found",
                            content = @Content(
                                    schema = @Schema(implementation = ErrorResponseDto.class)
                            )
                    )
            }
    )
    @GetMapping("getById")
    public ResponseEntity<CourseDto> getCourseById(@RequestParam Long courseId) {
            CourseDto courseDto = icourseService.getCourseById(courseId);
            return  ResponseEntity.status(HttpStatus.OK).body(courseDto);

    }

    @Operation(
            summary = "Get All Course"
    )
 @ApiResponses(
         {
                 @ApiResponse(
                         responseCode = "200",
                         description = "Successfully return course"
                 ),
                 @ApiResponse(
                         responseCode = "404",
                         description = "Course not found",
                         content = @Content(
                                 schema = @Schema(implementation = ErrorResponseDto.class)
                         )
                 )
         }
 )
    @GetMapping("/getAll")
    public ResponseEntity<Queue<CourseDto>> getAllCourse() {
        Queue<CourseDto> courseDtoList = icourseService.getAllCourse();
        return  ResponseEntity.status(HttpStatus.OK).body(courseDtoList);
    }

    @Operation(
            summary = "Update Course"
    )

    @ApiResponses(
            {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully return course"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Course not found",
                            content = @Content(
                                    schema = @Schema(implementation = ErrorResponseDto.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "409",
                            description = "Course already exist",
                            content = @Content(
                                    schema = @Schema(implementation = ErrorResponseDto.class)
                            )
                    )
            }
    )
    @PutMapping("/update/{courseId}")
    public ResponseEntity<ResponseDto> updateCourse(
            @PathVariable Long courseId,
            @Valid @RequestBody CourseDto courseDto) {
        try {
            icourseService.updateCourse(courseDto, courseId);
            return  ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(CourseConstants.STATUS_200, CourseConstants.MESSAGE_200));
        } catch (CourseNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseDto(CourseConstants.STATUS_404, CourseConstants.MESSAGE_404));
        } catch (CourseAlreadyExistException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ResponseDto(CourseConstants.STATUS_409, CourseConstants.MESSAGE_409));
        }
    }


    @Operation(
            summary = "Delete Course"
    )

    @ApiResponses(
            {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully return course"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Course not found",
                            content = @Content(
                                    schema = @Schema(implementation = ErrorResponseDto.class)
                            )
                    )
            }
    )
    @DeleteMapping("/delete/{courseId}")
    public ResponseEntity<String> deleteCourse(@PathVariable Long courseId) {
        try {
            icourseService.deleteCourse(courseId);
            return  ResponseEntity.status(HttpStatus.OK).body(CourseConstants.MESSAGE_200);
        } catch (CourseNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(CourseConstants.MESSAGE_404);
        }
    }


    @Operation(
            summary = "Generate Course Pdf"
    )

    @GetMapping("/pdf")
    public ResponseEntity<byte[]> generateCoursePdf() {
        Queue<CourseDto> courseQueue = icourseService.getAllCourse();
        byte[] pdfBytes = ipdfGeneratorService.generateCoursePdf(courseQueue);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("filename", "courses.pdf");

        return ResponseEntity.ok()
                .headers(headers)
                .body(pdfBytes);
    }

}
