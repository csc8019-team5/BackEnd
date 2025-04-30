package uk.ac.ncl.team5project.config;


import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import uk.ac.ncl.team5project.util.JwtUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;


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

    private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationFilter.class);

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
        try {
            String jwt = parseJwt(request);
            logger.debug("Received request for URL: {}", request.getRequestURL());
            logger.debug("Authorization header: {}", request.getHeader("Authorization"));
            
            if (jwt != null) {
                logger.debug("JWT token found: {}", jwt);
                
                if (jwtUtil.validateJwtToken(jwt)) {
                    String username = jwtUtil.getUserNameFromJwtToken(jwt);
                    String role = jwtUtil.getRoleFromJwtToken(jwt);
                    
                    logger.debug("Valid JWT token - Username: {}, Role: {}", username, role);
                    
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        username, null, Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + role.toUpperCase())));
                    
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                    logger.debug("Authentication set in SecurityContext");
                } else {
                    logger.warn("Invalid JWT token");
                }
            } else {
                logger.debug("No JWT token found in request");
            }
        } catch (ExpiredJwtException e) {
            logger.error("JWT token has expired: {}", e.getMessage());
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Token has expired");
            return;
        } catch (Exception e) {
            logger.error("Authentication error: {}", e.getMessage());
        }

        filterChain.doFilter(request, response);
    }

    /**
     * Parses JWT token from Authorization header.
     * Expected format: Bearer <token>
     * @param request HTTP request
     * @return JWT token string or null if invalid
     */
    private String parseJwt(HttpServletRequest request) {
        String headerAuth = request.getHeader("Authorization");
        logger.debug("Parsing JWT from Authorization header: {}", headerAuth);

        if (StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")) {
            return headerAuth.substring(7);
        }

        return null;
    }
} 