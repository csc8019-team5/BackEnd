package uk.ac.ncl.team5project.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import uk.ac.ncl.team5project.model.dto.LoginDTO;
import uk.ac.ncl.team5project.model.dto.RegisterDTO;
import uk.ac.ncl.team5project.service.UserService;
import uk.ac.ncl.team5project.util.Result;

import java.util.Random;

/**
 * @file UserController.java
 * @date 2025-04-01
 * @function_description:
 * Provides user registration, standard/admin login, and personal information update and retrieval interfaces.
 * @interface_description:
 * POST /v1/users/register - User registration
 * POST /v1/users/login - User login
 * POST /v1/users/admin/login - Admin login
 * PUT /v1/users/me - Update current user's info
 * GET /v1/users/me - Get current user's info
 * @calling_sequence:
 * Controller → UserService → UserMapper → Database
 * @arguments_description:
 * - Register DTO: Registration parameters, including username, email, password, etc
 * - Login DTO: Login parameters, including email and password
 * - username,  password:  Fields used to modify current user information
 * @list_of_subordinate_classes:
 * UserService, RegisterDTO, LoginDTO, Result
 * @discussion:
 * Registration and login endpoints are publicly accessible.
 * Interfaces like profile update and user info retrieval require authentication via JWT token.
 * @development_history:
 * Created on 2025-04-01 as part of team5 wishlist module
 * @designer: wensi huang
 * @reviewer: wensi huang
 * @review_date: 2025-04-18
 * @modification_date: 2025-04-18
 * @description: Controller class for handling user-related HTTP requests
 */
@RestController
@RequestMapping("/v1/users")
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * Registers a new user.
     * Accepts a RegisterDTO in the request body.
     * Publicly accessible.
     */
    @PostMapping("/register")
    public Result<?> register(@RequestBody RegisterDTO user) {
        return userService.register(user);
    }

    /**
     * Authenticates a standard user and returns a JWT token.
     * Accepts a LoginDTO in the request body.
     * Publicly accessible.
     */
    @PostMapping("/login")
    public Result<?> login(@RequestBody LoginDTO user) {
        return userService.login(user);
    }

    /**
     * Authenticates an admin user and returns a JWT token.
     * Accepts a LoginDTO in the request body.
     * Publicly accessible.
     */
    @PostMapping("/admin/login")
    public Result<?> adminLogin(@RequestBody LoginDTO user) {
        return userService.adminLogin(user);
    }

    /**
     * Updates the current user's profile.
     * Supports updating username and/or password.
     * Requires JWT authentication.
     */
    @PutMapping("/me")
    public Result<?> update(
            @RequestParam(required = false) String username,
            @RequestParam(required = false) String password
    ) {
        return userService.update(username, password);
    }

    /**
     * Retrieves the current authenticated user's profile.
     * Requires JWT authentication.
     */
    @GetMapping("/me")
    public Result<?> getInfo() {
        return userService.getInfo();
    }

}
