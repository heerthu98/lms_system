//package com.lms.enrollment_service.config;
//
//import org.springframework.cloud.gateway.route.RouteLocator;
//import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class GatewayConfig {
//
//    @Bean
//    public RouteLocator customRoutes(RouteLocatorBuilder builder) {
//        return builder.routes()
//                .route("course_service", r -> r.path("/api/v2/course/**")
//                        .uri("http://localhost:8090")) // Course Service URL
//                .route("enrollment_service", r -> r.path("/api/v1/enrollment/**")
//                        .uri("http://localhost:9000")) // Enrollment Service URL
//                .build();
//    }
//}
//
