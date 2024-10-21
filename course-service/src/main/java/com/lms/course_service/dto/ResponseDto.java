package com.lms.course_service.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Course Response DTO")
public class ResponseDto {

    @Schema(description = "Status Code")
    private String statusCode;

    @Schema(description = "Status Message")
    private String StatusMessage;
}
