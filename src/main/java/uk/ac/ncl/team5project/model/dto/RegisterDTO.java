package uk.ac.ncl.team5project.model.dto;

import lombok.Data;

/**
 * @file RegisterDTO.java
 * @date
 * @function_description: Data Transfer Object for user registration requests.
 * @interface_description: Carries user registration data including username, password, and email.
 * @calling_sequence: Frontend → Controller → Service
 * @arguments_description: String username, String password, String email
 * @list_of_subordinate_classes: None
 * @discussion: Used to encapsulate user-provided registration details.
 * @development_history: Created on 2025-04-01 as part of registration module.
 * @designer: wensi huang
 * @reviewer: wensi huang
 * @review_date: 2025-04-18
 * @modification_date: 2025-04-18
 * @description: DTO for transferring user registration input fields.
 */

@Data
public class RegisterDTO {
    private String username;
    private String password;
    private String email;


}
