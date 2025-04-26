package uk.ac.ncl.team5project.model.vo;

import lombok.Data;

/**
 * @file LoginVO.java
 * @date 2025-04-01
 * @function_description: View Object for user login response.
 * @interface_description: Contains the JWT token returned upon successful login.
 * @calling_sequence: Service → Controller → Frontend
 * @arguments_description: String token
 * @list_of_subordinate_classes: None
 * @discussion: Used to transmit login success response including token for authentication.
 * @development_history: Created on 2025-04-01 as part of login response structure.
 * @designer: wensi huang
 * @reviewer: wensi huang
 * @review_date: 2025-04-18
 * @modification_date: 2025-04-18
 * @description: Encapsulates JWT token in the login response.
 */
@Data
public class LoginVO {
    // JWT token returned to the client
    private String token;
}
