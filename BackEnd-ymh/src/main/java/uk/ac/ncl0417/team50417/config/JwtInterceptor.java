package uk.ac.ncl0417.team50417.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;
import uk.ac.ncl0417.team50417.util.JwtUtil;
/**
 * Class: JwtInterceptor
 * File: JwtInterceptor.java
 * Created on: 2025/4/25
 * Author: menghui yao
 *
 * Description:
 * <pre>
 *     Function: Its main purpose is to valid token is right and contain the username.
 *     Interface Description:
 *         - Calling Sequence:
 *                              The preHandle() method is called for every incoming HTTP request. It checks for
 *                              the presence and validity of the JWT token. If valid, it allows the request to proceed;
 *                              otherwise, it rejects the request with a 401 Unauthorized response.
 *         - Argument Description:
 *                              HttpServletRequest request: The request object that contains the HTTP request data.
 *                              HttpServletResponse response: The response object to send back to the client.
 *                              Object handler: The handler for the controller method.
 *         - List of Subordinate Classes:
 *                              JwtUtil: A utility class used to extract the username from the token and validate its integrity.
 *
 * </pre>
 *
 * Development History:
 * <pre>
 *     Designer: menghui yao
 *     Reviewer: menghui yao
 *     Review Date: 2025/4/25
 *     Modification Date: 2025/4/25
 *     Modification Description: none
 * </pre>
 */
public class JwtInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Missing or invalid Authorization header");
            return false;
        }

        String token = authHeader.substring(7);

        try {
            String username = JwtUtil.extractUsername(token);
            if (JwtUtil.validateToken(token, username)) {
                return true; // verify ok, pass
            }
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Token validation failed: " + e.getMessage());
            return false;
        }

        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.getWriter().write("Invalid or expired token");
        return false;
    }
}