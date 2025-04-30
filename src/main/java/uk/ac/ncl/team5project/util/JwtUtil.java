package uk.ac.ncl.team5project.util;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @file JwtUtil.java
 * @date 2025-04-01
 * @function_description: Utility class for generating, parsing, and validating JWT tokens.
 * @interface_description: Provides methods for issuing tokens with roles and extracting user info from tokens.
 * @calling_sequence: Controller/Filter → JwtUtil → Token handling
 * @arguments_description: String username, String role, String token
 * @list_of_subordinate_classes: None
 * @discussion: Central utility for handling authentication via JWT in a stateless security system.
 * @development_history: Created on 2025-04-01 as part of security module.
 * @designer: wensi huang
 * @reviewer: wensi huang
 * @review_date: 2025-04-18
 * @modification_date: 2025-04-18
 * @description: Handles all JWT-related operations including creation and validation.
 */
@Component
public class JwtUtil {
    private static final Logger logger = LoggerFactory.getLogger(JwtUtil.class);

    @Value("${jwt.secret}")
    private String jwtSecret;
    @Value("${jwt.expiration}")
    private long jwtExpirationMs;

    /**
     * Generate a JWT token containing username and role.
     * @param username the subject (user identity)
     * @param role the user's role (e.g., user, admin)
     * @return signed JWT token string
     */
    public String generateJwtToken(String username, String role){
        logger.debug("Generating JWT token for username: {}, role: {}", username, role);
        
        String token = Jwts.builder()
                .setSubject(username)
                .claim("role", role)
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
                
        logger.debug("Generated token: {}", token);
        return token;
    }

    /**
     * Extract username from JWT token.
     * @param token JWT token
     * @return username (subject)
     */
    public String getUserNameFromJwtToken(String token){
        String username = Jwts.parser().setSigningKey(jwtSecret).
                parseClaimsJws(token).getBody().getSubject();
        logger.debug("Extracted username from token: {}", username);
        return username;
    }

    /**
     * Extract role from JWT token.
     * @param token JWT token
     * @return user role
     */
    public String getRoleFromJwtToken(String token){
        String role = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().get("role", String.class);
        logger.debug("Extracted role from token: {}", role);
        return role;
    }

    /**
     * Validate the integrity and expiration of a JWT token.
     * @param authToken token to validate
     * @return true if valid, false otherwise
     */
    public boolean validateJwtToken(String authToken){
        try{
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            logger.debug("JWT token validation successful");
            return true;
        }
        catch (JwtException e){
            logger.error("Invalid JWT signature: {}", e.getMessage());
        }
        return false;
    }
}
