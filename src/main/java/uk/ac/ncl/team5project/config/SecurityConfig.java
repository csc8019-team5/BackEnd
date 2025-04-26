package uk.ac.ncl.team5project.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
/**
 * @file SecurityConfig.java
 * @date 2025-04-01
 * @function_description: Configures Spring Security settings including JWT integration and endpoint access rules.
 * @interface_description: Defines the security filter chain and access control rules for HTTP endpoints.
 * @calling_sequence: Spring Boot startup → Load SecurityConfig → Apply security filter chain
 * @arguments_description: HttpSecurity http
 * @list_of_subordinate_classes: JwtAuthEntryPoint, JwtAuthenticationFilter
 * @discussion: Enables stateless session management and injects JWT authentication filter.
 * @development_history: Created on 2025-04-01 as part of security module
 * @designer: wensi huang
 * @reviewer: wensi huang
 * @review_date: 2025-04-18
 * @modification_date: 2025-04-18
 * @description: Central security configuration using Spring Security with JWT authentication support.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    private JwtAuthEntryPoint unauthorizedHandler;

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;
    /**
     * Configures HTTP security including CSRF disabling, stateless session, JWT filter, and access rules.
     * @param http HttpSecurity object
     * @return configured SecurityFilterChain
     * @throws Exception in case of configuration errors
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .exceptionHandling(exception -> exception
                        .authenticationEntryPoint(unauthorizedHandler)
                )
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/v1/users/register","/v1/users/admin/login",
                                "/v1/users/login","v1/books/*/reviews","v1/books","v1/books/*").permitAll()
                        .anyRequest().authenticated()
                );
        // Add JWT filter before default username/password authentication filter
        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
