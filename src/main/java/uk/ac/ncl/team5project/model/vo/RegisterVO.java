package uk.ac.ncl.team5project.model.vo;

import lombok.Data;

/**
 * @file RegisterVO.java
 * @date 2025-04-01
 * @function_description: View Object for user registration response.
 * @interface_description: Returns basic user information after successful registration.
 * @calling_sequence: Service → Controller → Frontend
 * @arguments_description: Integer id, String username, String email
 * @list_of_subordinate_classes: None
 * @discussion: Simplifies the client-side handling of registration success by returning essential info.
 * @development_history: Created on 2025-04-01 as part of registration response structure.
 * @designer: wensi huang
 * @reviewer: wensi huang
 * @review_date: 2025-04-18
 * @modification_date: 2025-04-18
 * @description: Encapsulates user info returned after successful registration.
 */
@Data
public class RegisterVO {
    private Integer id;
    private String username;
    private String email;
}
