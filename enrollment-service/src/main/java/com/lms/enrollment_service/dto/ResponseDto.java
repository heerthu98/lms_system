package com.lms.enrollment_service.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Schema(name="ResponseDto",description = "Response DTO")
public class ResponseDto {

    @Schema(description = "Status Code")
    private String statusCode;

    @Schema(description = "Status Message")
    private String StatusMessage;
}
