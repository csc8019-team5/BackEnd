package uk.ac.ncl.team5project.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @file CorsConfig.java
 * @date 2025-04-01
 * @function_description: Configuration class to enable CORS support for cross-origin requests.
 * @interface_description: Applies to all endpoints (/**), allowing access from all origins and standard HTTP methods.
 * @calling_sequence: Automatically invoked by Spring Boot at startup.
 * @arguments_description: Configures allowed origins, headers, methods, and credentials.
 * @list_of_subordinate_classes: None
 * @discussion: Enables front-end applications hosted on different ports/domains to access back-end APIs.
 * @development_history: Created on 2025-04-01 as part of team5 config module
 * @designer: wensi huang
 * @reviewer: wensi huang
 * @review_date: 2025-04-18
 * @modification_date: 2025-04-18
 * @description: Spring WebMvcConfigurer implementation for cross-origin support (CORS).
 */
@Configuration
public class CorsConfig implements WebMvcConfigurer {

    /**
     * Configure global CORS rules.
     * Allows all origins, headers, and standard HTTP methods to access the back-end APIs.
     * This is required for front-end and back-end projects running on different ports.
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOriginPatterns("*")
                .allowedMethods("GET", "HEAD", "POST", "PUT", "DELETE", "OPTIONS")
                .allowCredentials(true)
                .maxAge(3600)
                .allowedHeaders("*");
    }

}
