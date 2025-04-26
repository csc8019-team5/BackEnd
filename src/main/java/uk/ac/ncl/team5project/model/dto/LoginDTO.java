package uk.ac.ncl.team5project.model.dto;

import lombok.Data;

/**
 * @file LoginDTO.java
 * @date 2025-04-01
 * @function_description: Data Transfer Object for user login request.
 * @interface_description: Carries login credentials (email and password) from client to server.
 * @calling_sequence: Frontend → Controller → Service
 * @arguments_description: String email, String password
 * @list_of_subordinate_classes: None
 * @discussion: Used to encapsulate user login credentials securely.
 * @development_history: Created on 2025-04-01 as part of login functionality.
 * @designer: wensi huang
 * @reviewer: wensi huang
 * @review_date: 2025-04-18
 * @modification_date: 2025-04-18
 * @description: A simple DTO containing fields required for login.
 */
@Data
public class LoginDTO {
    private String email;
    private String password;
}
