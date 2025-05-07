package uk.ac.ncl.team5project.model.vo;

import lombok.Data;

/**
 * @file LoginVO.java
 * @date 2025-04-01
 * @function_description: View Object for user login response.
 * @interface_description: Contains the JWT token, username, and userId returned upon successful login.
 * @calling_sequence: Service → Controller → Frontend
 * @arguments_description: String token, String username, int userId
 * @list_of_subordinate_classes: None
 * @discussion: Used to transmit login success response including token for authentication.
 * @development_history: Created on 2025-04-01 as part of login response structure.
 * @designer: wensi huang
 * @reviewer: wensi huang
 * @review_date: 2025-05-07
 * @modification_date: 2025-05-07
 * @description: Encapsulates user login response data including JWT token, username, and userId.
 */

@Data
public class LoginVO {
    // JWT token returned to the client
    private String token;
    private String username;
    private int userId;
}
