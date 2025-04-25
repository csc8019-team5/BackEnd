package uk.ac.ncl.team5project.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Class: SecurityConfig
 * File: SecurityConfig.java
 * Created on: 24/04/2025
 * Author: Yixin Zhang
 *
 * Description:
 * <pre>
 *     Function: Configures Spring Security for the application, specifying URL-based security,
 *               HTTP security settings, and JWT authentication filter integration. Controls
 *               which endpoints require authentication and which are publicly accessible.
 *     Interface Description:
 *         - securityFilterChain: Configures the security filter chain for HTTP requests.
 *     Calling Sequence:
 *         - Automatically used by Spring Security framework during application startup.
 *         - Intercepts and processes all incoming HTTP requests.
 *     Argument Description:
 *         - http: HttpSecurity object to configure security settings.
 *     List of Subordinate Classes: JwtFilter.
 * </pre>
 *
 * Development History:
 * <pre>
 *     Designer: Yixin Zhang
 *     Reviewer: Yixin Zhang
 *     Review Date: 25/04/2025
 *     Modification Date: 25/04/2025
 *     Modification Description: Implemented with Spring Security 6.x configuration style.
 * </pre>
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JwtFilter jwtFilter;

    public SecurityConfig(JwtFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                // Publicly accessible endpoints
                .requestMatchers("/v1/auth/**").permitAll()
                .requestMatchers("/v1/books/**").permitAll()  // Book browsing does not require authentication
                .requestMatchers("/v1/categories/**").permitAll()  // Category endpoints do not require authentication
                // Endpoints that require authentication
                .requestMatchers("/v1/loans/**").authenticated()
                .anyRequest().authenticated()
            )
            .sessionManagement(session -> session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            )
            .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
            .build();
    }
}
