package uk.ac.ncl.team5project.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Class: JwtFilter
 * File: JwtFilter.java
 * Created on: 24/04/2025
 * Author: Yixin Zhang
 *
 * Description:
 * <pre>
 *     Function: Intercepts HTTP requests to validate JWT tokens and establish security context.
 *               Acts as the gatekeeper for protected resources in the library system.
 *     Interface Description:
 *         - doFilterInternal: Processes requests to extract and validate JWT tokens.
 *     Calling Sequence:
 *         - Automatically invoked by Spring Security for each incoming request.
 *         - Executed before request reaches protected endpoints.
 *     Argument Description:
 *         - request: HTTP request being processed.
 *         - response: HTTP response being returned.
 *         - chain: FilterChain for request processing.
 *     List of Subordinate Classes: JwtUtil.
 * </pre>
 *
 * Development History:
 * <pre>
 *     Designer: Yixin Zhang
 *     Reviewer: Yixin Zhang
 *     Review Date: 25/04/2025
 *     Modification Date: 25/04/2025
 *     Modification Description: Initial implementation integrated with Spring Security.
 * </pre>
 */
@Component
public class JwtFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;

    public JwtFilter(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    /**
     * Core filter method that processes each HTTP request to validate JWT authentication.
     * Extracts JWT token from Authorization header, validates it, and sets the
     * authentication context if valid.
     *
     * @param request The HTTP request being processed
     * @param response The HTTP response being prepared
     * @param chain The filter chain for request processing
     * @throws ServletException If an error occurs during filtering
     * @throws IOException If an I/O error occurs during filtering
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        
        final String authHeader = request.getHeader("Authorization");
        
        String token = null;
        Integer userId = null;
        
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            token = authHeader.substring(7);
            try {
                userId = jwtUtil.extractUserId(token);
            } catch (Exception e) {
                // Invalid token, do nothing
            }
        }
        
        // If a valid userId is found and authentication is not yet set
        if (userId != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            if (jwtUtil.validateToken(token)) {
                // Create authentication object
                UsernamePasswordAuthenticationToken authentication = 
                    new UsernamePasswordAuthenticationToken(userId, null, new ArrayList<>());
                
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);
                
                // Store userId in request attribute for controller access
                request.setAttribute("userId", userId);
            }
        }
        
        chain.doFilter(request, response);
    }
}
