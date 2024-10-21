package com.lms.course_service;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@OpenAPIDefinition(
		info = @Info(
				title = "LMS Course Service",
				description = "LMS Course Service API",
				version = "v1",
				contact = @Contact(
						name = "LMS",
						email = "LMS@example.com",
						url = "https://www.lmscreative.com"
				),
				license= @License(
						name = "Apache 2.0",
						url = "https://www.LMSCreative.com"
				)
		),
		externalDocs = @ExternalDocumentation(
				description = "LMS Documentation",
				url = "https://www.lmscreative.com"
		)
)
public class CourseServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CourseServiceApplication.class, args);
	}

}
