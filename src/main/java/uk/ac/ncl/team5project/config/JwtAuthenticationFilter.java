package uk.ac.ncl.team5project.config;


import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import uk.ac.ncl.team5project.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;


import java.io.IOException;
import java.util.Collections;

/**
 * @file JwtAuthenticationFilter.java
 * @date 2025-04-01
 * @function_description: JWT filter to extract and validate token from each request, and store user identity in SecurityContext.
 * @interface_description: Applies to all incoming HTTP requests to validate JWT and authenticate the user.
 * @calling_sequence: HTTP request → JwtAuthenticationFilter → JwtUtil → SecurityContext
 * @arguments_description: HttpServletRequest, HttpServletResponse, FilterChain
 * @list_of_subordinate_classes: JwtUtil
 * @discussion: Ensures secure authentication for protected endpoints by processing JWT tokens per request.
 * @development_history: Created on 2025-04-01 as part of security module
 * @designer: wensi huang
 * @reviewer: wensi huang
 * @review_date: 2025-04-18
 * @modification_date: 2025-04-18
 * @description: Custom Spring Security filter for handling JWT authentication.
 */
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;

    /**
     * Filter method that checks for JWT in Authorization header.
     * If valid, sets authentication in Spring Security context.
     * @param request HTTP request
     * @param response HTTP response
     * @param filterChain filter chain for continuing execution
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        try{
            String jwt = parseJwt(request);
            if(jwt != null && jwtUtil.validateJwtToken(jwt)){
                String username = jwtUtil.getUserNameFromJwtToken(jwt);
                String role = jwtUtil.getRoleFromJwtToken(jwt);

                // Create authentication object using extracted username and role
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        username, null, Collections.singleton(() -> role)
                );
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                // Store authentication in security context
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        } catch (Exception e){
            // Exception handling during authentication
        }

        filterChain.doFilter(request, response);
    }

    /**
     * Parses JWT token from Authorization header.
     * Expected format: Bearer <token>
     * @param request HTTP request
     * @return JWT token string or null if invalid
     */
    private String parseJwt(HttpServletRequest request){
        String headerAuth = request.getHeader(HttpHeaders.AUTHORIZATION);
        if(StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")){
            return headerAuth.substring(7);
        }
        return null;
    }
}

