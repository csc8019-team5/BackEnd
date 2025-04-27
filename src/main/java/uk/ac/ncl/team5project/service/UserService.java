package uk.ac.ncl.team5project.service;

import org.springframework.http.ResponseEntity;
import uk.ac.ncl.team5project.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import uk.ac.ncl.team5project.model.dto.LoginDTO;
import uk.ac.ncl.team5project.model.dto.RegisterDTO;
import uk.ac.ncl.team5project.util.Result;

/**
 * @file UserService.java
 * @date 2025-04-01
 * @function_description: Service interface defining user-related operations.
 * @interface_description: Includes methods for registration, login, profile update, and info retrieval.
 * @calling_sequence: Controller → UserService → UserMapper
 * @arguments_description: LoginDTO, RegisterDTO, String userName, String password
 * @list_of_subordinate_classes: LoginDTO, RegisterDTO, User
 * @discussion: Forms the business logic layer for managing user accounts and authentication.
 * @development_history: Created on 2025-04-01 as part of user service module.
 * @designer: wensi huang
 * @reviewer: wensi huang
 * @review_date: 2025-04-18
 * @modification_date: 2025-04-18
 * @description: Defines core user account service interfaces used across the application.
 */
public interface UserService extends IService<User> {
    // Handle user registration
    Result<?> register(RegisterDTO user);
    // Handle user login
    Result<?> login(LoginDTO user);
    // Update user info
    Result<?> update(String userName,String password);
    // Retrieve current user info
    Result<?> getInfo();
    // Admin login handler
    Result<?> adminLogin(LoginDTO user);
}
