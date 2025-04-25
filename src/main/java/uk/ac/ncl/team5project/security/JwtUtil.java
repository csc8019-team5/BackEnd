package uk.ac.ncl.team5project.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * Class: JwtUtil
 * File: JwtUtil.java
 * Created on: 24/04/2025
 * Author: Yixin Zhang
 *
 * Description:
 * <pre>
 *     Function: Provides JWT (JSON Web Token) utilities for generating, validating and parsing tokens,
 *               supporting secure authentication mechanisms for the library system.
 *     Interface Description:
 *         - extractUserId: Extracts the user ID from the JWT token.
 *         - extractExpiration: Extracts the expiration date from the JWT token.
 *         - extractClaim: Extracts any claim from the JWT token using a function.
 *         - extractAllClaims: Extracts all claims from the JWT token.
 *         - generateToken: Generates a JWT token for a given user ID.
 *         - isTokenExpired: Checks if a token is expired.
 *         - validateToken: Validates a token's authenticity and expiration.
 *     Calling Sequence:
 *         - Generate Token: String token = jwtUtil.generateToken(userId);
 *         - Extract User ID: Integer userId = jwtUtil.extractUserId(token);
 *         - Validate Token: boolean isValid = jwtUtil.validateToken(token);
 *     Argument Description:
 *         - userId: The user identifier to be embedded in the token.
 *         - token: The JWT token string to be processed.
 *         - claims: Map of claims to be included in the token.
 *         - claimsResolver: Function to extract specific claim from Claims object.
 *     List of Subordinate Classes: None.
 * </pre>
 *
 * Development History:
 * <pre>
 *     Designer: Yixin Zhang
 *     Reviewer: Yixin Zhang
 *     Review Date: 25/04/2025
 *     Modification Date: 25/04/2025
 *     Modification Description: Initial implementation with HMAC-SHA256 signing.
 * </pre>
 */
@Component
public class JwtUtil {
    // Security key - should be stored in configuration files in production environments
    private final String SECRET_KEY = "yourSecretKeyHereShouldBeAtLeast256BitsLongForSecurity";
    private final Key key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    
    /**
     * Extracts the user ID from a JWT token.
     * 
     * @param token JWT token string
     * @return User ID as Integer
     */
    public Integer extractUserId(String token) {
        return Integer.valueOf(extractClaim(token, claims -> claims.get("userId", String.class)));
    }
    
    /**
     * Extracts the expiration date from a JWT token.
     * 
     * @param token JWT token string
     * @return Expiration date
     */
    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }
    
    /**
     * Extracts a specific claim from a JWT token using a function.
     * 
     * @param <T> Type of the claim to extract
     * @param token JWT token string
     * @param claimsResolver Function to extract specific claim
     * @return Extracted claim value
     */
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }
    
    /**
     * Extracts all claims from a JWT token.
     * Internal helper method used by other public extraction methods.
     * 
     * @param token JWT token string to parse
     * @return All claims contained in the token
     * @throws JwtException If the token is invalid or cannot be parsed
     */
    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
    
    /**
     * Generates a JWT token for a given user ID.
     * 
     * @param userId User ID to include in token
     * @return Generated JWT token string
     */
    public String generateToken(Integer userId) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", userId.toString());
        return createToken(claims);
    }
    
    /**
     * Creates a JWT token with provided claims.
     * Sets issuedAt to current time and expiration to 24 hours later.
     * 
     * @param claims Map of claims to include in the token
     * @return Signed JWT token string
     */
    private String createToken(Map<String, Object> claims) {
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24)) // 24 hours validity
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }
    
    /**
     * Checks if a JWT token is expired.
     * 
     * @param token JWT token string
     * @return true if expired, false otherwise
     */
    public boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }
    
    /**
     * Validates a JWT token's authenticity and expiration.
     * 
     * @param token JWT token string
     * @return true if valid, false otherwise
     */
    public boolean validateToken(String token) {
        try {
            return !isTokenExpired(token);
        } catch (Exception e) {
            return false;
        }
    }
}
