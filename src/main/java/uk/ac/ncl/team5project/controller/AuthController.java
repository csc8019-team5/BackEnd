package uk.ac.ncl.team5project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uk.ac.ncl.team5project.security.JwtUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * Class: AuthController
 * File: AuthController.java
 * Created on: 24/04/2025
 * Author: Yixin Zhang
 *
 * Description:
 * <pre>
 *     Function: Handles user authentication and generates JWT tokens for authenticated users,
 *               providing a secure access mechanism for the library management system.
 *     Interface Description:
 *         - login: Authenticates user credentials and issues a JWT token.
 *     Calling Sequence:
 *         - User Login (POST /v1/auth/login):
 *           Request: POST /v1/auth/login with {"email": "user@example.com", "password": "password"}
 *           Response: {"token": "jwt_token_value", "userId": 1}
 *     Argument Description:
 *         - loginRequest: Map containing login credentials (email and password).
 *         - email: User's email address for authentication.
 *         - password: User's password for verification.
 *         - token: JWT token generated upon successful authentication.
 *     List of Subordinate Classes: JwtUtil.
 * </pre>
 *
 * Development History:
 * <pre>
 *     Designer: Yixin Zhang
 *     Reviewer: Yixin Zhang
 *     Review Date: 25/04/2025
 *     Modification Date: 25/04/2025
 *     Modification Description: Initial implementation with basic authentication logic.
 * </pre>
 */
@RestController
@RequestMapping("/v1/auth")
public class AuthController {

    private final JwtUtil jwtUtil;
    
    @Autowired
    public AuthController(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> loginRequest) {
        String email = loginRequest.get("email");
        String password = loginRequest.get("password");
        

        if ("user@example.com".equals(email) && "password".equals(password)) {
            // 用户ID为1
            String token = jwtUtil.generateToken(1);
            
            Map<String, Object> response = new HashMap<>();
            response.put("token", token);
            response.put("userId", 1);
            
            return ResponseEntity.ok(response);
        }
        
        return ResponseEntity.status(401).body("Invalid credentials");
    }
}
