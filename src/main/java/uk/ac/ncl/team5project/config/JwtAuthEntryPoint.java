package uk.ac.ncl.team5project.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
/**
 * @file JwtAuthEntryPoint.java
 * @date 2025-04-01
 * @function_description: Entry point handler for unauthorized access attempts.
 * @interface_description: Triggered automatically by Spring Security when an unauthenticated user accesses a protected endpoint.
 * @calling_sequence: Protected endpoint → AuthenticationEntryPoint → commence()
 * @arguments_description: HttpServletRequest, HttpServletResponse, AuthenticationException
 * @list_of_subordinate_classes: None
 * @discussion: Ensures that unauthorized access returns a 401 status with a standard error message.
 * @development_history: Created on 2025-04-01 as part of security module
 * @designer: wensi huang
 * @reviewer: wensi huang
 * @review_date: 2025-04-18
 * @modification_date: 2025-04-18
 * @description: Custom AuthenticationEntryPoint to handle unauthorized access exceptions.
 */
@Component
public class JwtAuthEntryPoint implements AuthenticationEntryPoint {

    /**
     * Called when an unauthenticated user tries to access a protected resource.
     * Sends a 401 Unauthorized error response.
     * @param request the HTTP request
     * @param response the HTTP response
     * @param authException the exception thrown by Spring Security
     * @throws IOException in case of I/O errors
     */
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException {
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
    }
}
